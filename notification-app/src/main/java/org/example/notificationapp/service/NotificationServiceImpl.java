package org.example.notificationapp.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.entity.enums.NotificationChannel;
import org.example.notificationapp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    @Value("${mail.address}")
    private String emailFrom;

    @Override
    public void changeNotification(Notification notification) {
        Notification existingNotification =
                notificationRepository.findByUserNameAndTabNameAndChannel(notification.getUserName(), notification.getTabName(), notification.getChannel())
                .or(() -> notificationRepository.findByUserMailAndTabNameAndChannel(notification.getUserMail(), notification.getTabName(), notification.getChannel()))
                .orElse(notification);

        existingNotification = mergeNotification(existingNotification, notification);
        log.info("Пользователь {} с id {}", notification.getUserName(), notification.getUserId());
        notificationRepository.save(existingNotification);
    }

    @Override
    public void changeNotificationEnable(String findParameter, String parameter, Boolean enable) {
        List<Notification> notifications = Collections.emptyList();
        if (findParameter.equals("bot"))
            notifications = notificationRepository.findAllByUserName(parameter)
                    .stream()
                    .peek(notification -> notification.setEnable(enable))
                    .toList();
        else if (findParameter.equals("mail"))
            notifications = notificationRepository.findAllByUserMail(parameter)
                    .stream()
                    .peek(notification -> notification.setEnable(enable))
                    .toList();

        if (!notifications.isEmpty()) {
            notificationRepository.saveAll(notifications);
        }
    }

    @Override
    public void deleteChannel(String findParameter, String parameter) {
        List<Long> ids = Collections.emptyList();
        if (findParameter.equals("bot"))
            ids = notificationRepository.findAllByUserNameAndChannel(parameter, NotificationChannel.TELEGRAM)
                    .stream()
                    .map(Notification::getId)
                    .toList();
        else if (findParameter.equals("mail"))
            ids = notificationRepository.findAllByUserMailAndChannel(parameter, NotificationChannel.MAIL)
                    .stream()
                    .map(Notification::getId)
                    .toList();

        if (!ids.isEmpty()) {
            notificationRepository.deleteAllById(ids);
        }
    }

    @Override
    public SendDocument getFileForBot(String userName, File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + file.getAbsolutePath());
        }

        Notification notification = notificationRepository.getFirstByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        SendDocument document = new SendDocument();
        document.setChatId(notification.getUserId());
        document.setDocument(new InputFile(file, file.getName()));

        return document;
    }

    @Override
    public void sendFileMail(String to, File file) {
        try {
            if (!file.exists()) {
                throw new IllegalArgumentException("Файл не найден: " + file.getAbsolutePath());
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setFrom(emailFrom);
            helper.setSubject("Готовый документ");
            helper.setText("Ваш пдф-документ во вложении.");
            helper.addAttachment(file.getName(), new FileSystemResource(file));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Ошибка отправки почты: {}", e.getMessage(), e);
        }
    }

    private Notification mergeNotification(Notification existingNotification, Notification notification) {
        return Notification.builder()
                .id(existingNotification.getId())
                .userId(Optional.ofNullable(existingNotification.getUserId())
                        .orElse(notification.getUserId()))
                .userName(Optional.ofNullable(existingNotification.getUserName())
                        .orElse(notification.getUserName()))
                .userMail(Optional.ofNullable(existingNotification.getUserMail())
                        .orElse(notification.getUserMail()))
                .tabName(existingNotification.getTabName())
                .state(notification.getState())
                .channel(existingNotification.getChannel())
                .enable(notification.getEnable())
                .build();
    }

}
