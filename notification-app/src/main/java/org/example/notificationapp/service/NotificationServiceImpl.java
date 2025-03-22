package org.example.notificationapp.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.entity.enums.NotificationChannel;
import org.example.notificationapp.entity.enums.StateEnum;
import org.example.notificationapp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final TelegramService telegramService;
    private final JavaMailSender mailSender;

    @Value("${bot.template}")
    private String message_bot;

    @Value("${mail.template}")
    private String message_mail;

    private static final String EMAIL_FROM = "stretensky.danila@yandex.ru";

    private final Map<NotificationChannel, Function<Notification, String>> channelKeyMap = new EnumMap<>(Map.of(
            NotificationChannel.MAIL, Notification::getUserMail,
            NotificationChannel.TELEGRAM, n -> n.getUserName() + ":" + n.getUserId()
    ));

    private final Map<NotificationChannel, Consumer<Map.Entry<String, String>>> notificationSenders = new EnumMap<>(Map.of(
            NotificationChannel.MAIL, entry -> sendMail(entry.getKey(), entry.getValue()),
            NotificationChannel.TELEGRAM, entry -> sendMessage(entry.getKey(), entry.getValue())
    ));

    @Override
    public void changeNotification(Notification notification) {
        log.info("Пользователь {} с id {}", notification.getUserName(), notification.getUserId());
        notificationRepository.save(notification);
    }

    @Scheduled(cron = "30 42 16 * * *")
    @Transactional
    protected void sendNotification() {
        log.info("Отправка уведомлений");
        Map<Map.Entry<NotificationChannel, String>, String> notificationMap =
                notificationRepository.findAllByState(StateEnum.RUNNING)
                        .stream()
                        .collect(Collectors.toMap(
                                this::getNotificationKey,
                                Notification::getTabName,
                                (oldValue, newValue) -> oldValue + ", " + newValue
                        ));

        notificationMap.forEach((key, value) ->
                notificationSenders.getOrDefault(key.getKey(), entry ->
                                log.warn("Неизвестный канал оповещения: {}", entry.getKey()))
                        .accept(Map.entry(key.getValue(), value))
        );

        List<Long> finishedIds = notificationRepository.findAllByState(StateEnum.FINISHED)
                .stream()
                .map(Notification::getId)
                .toList();

        if (!finishedIds.isEmpty()) {
            notificationRepository.deleteAllById(finishedIds);
        }
        log.info("Уведомления отправлены");
    }

    private Map.Entry<NotificationChannel, String> getNotificationKey(Notification notification) {
        return Map.entry(notification.getChannel(),
                channelKeyMap.getOrDefault(notification.getChannel(), n -> {
                    throw new IllegalArgumentException("Неизвестный тип канала оповещения: " + n.getChannel());
                }).apply(notification));
    }

    private void sendMessage(String userComb, String tabsName) {
        String[] userInfo = userComb.split(":");
        String formattedText = String.format(message_bot, userInfo[0], tabsName);
        telegramService.sendMessage(
                userInfo[1],
                formattedText
        );
    }

    private void sendMail(String mailTo, String tabsName) {
        try {
            String formattedText = String.format(message_mail, tabsName, EMAIL_FROM);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setSubject("Напоминание оформления документов");
            helper.setFrom(EMAIL_FROM);
            helper.setTo(mailTo);
            helper.setText(formattedText, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Ошибка отправки почты: {}", e.getMessage(), e);
        }
    }
}
