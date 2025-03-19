package org.example.notificationapp.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.repository.NotificationRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final TelegramService telegramService;

    private final JavaMailSender mailSender;

    private final static String NOTIFICATION_BOT_MESSAGE =
                    """
                    Добрый времени суток, %s!
                    Вы не завершили оформление документов: %s
                    """;

    private final static String NOTIFICATION_TEXT =
                    """
                    
                    <b>Доброе утро, дорогой пользователь!</b>
                    <br>
                    <p>Не забудь завершить заполнение следующих документов: %s</p>
                    <br><br>
                    <small>С уважением,</small>
                    <br>
                    <small>Команда BusinessPack</small>
                    <br>
                    <small><i>Связаться с нами: %s<i></small>
                    
                    """;
    private final static String EMAIL_FROM = "stretensky.danila@yandex.ru";

    private final static Function<Notification, String> keyMapMessage = n -> n.getUserName() + ":" + n.getUserId();
    private final static Function<Notification, String> keyMapMail =  Notification::getUserMail;

    @Override
    public void changeNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Scheduled(cron = "0 27 11 * * *")
    @Transactional
    protected void sendNotification() {
        Map<String, String> notificationsData = notificationRepository.findAllByState(Notification.StateEnum.RUNNING)
                .stream()
                .collect(Collectors.toMap(
                        keyMapMail,
                        Notification::getTabName,
                        (oldValue, newValue) -> oldValue + ", " + newValue));
        notificationsData
                .forEach((userKey, tabs) -> sendMail(userKey, tabs));

        Map<String, String> userNotifications = notificationRepository.findAllByState(Notification.StateEnum.RUNNING)
                .stream()
                .collect(Collectors.toMap(
                        keyMapMessage,
                        Notification::getTabName,
                        (oldValue, newValue) -> oldValue + ", " + newValue));

        userNotifications.forEach((userKey, tabs) -> {
            String[] userInfo = userKey.split(":");
            sendMessage(userInfo[1], userInfo[0], tabs);
        });

        notificationRepository.findAllByState(Notification.StateEnum.FINISHED)
                .forEach(this::removingFinishedTasks);

    }

    private void sendMessage(String userId, String userName, String tabs) {
        telegramService.sendMessage(
                userId,
                String.format(NOTIFICATION_BOT_MESSAGE, userName, tabs)
        );
    }

    private void sendMail(String mailTo, String tabNames) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setSubject("Напоминание оформления документов");
            helper.setFrom(EMAIL_FROM);
            helper.setTo(mailTo);

            helper.setText(String.format(NOTIFICATION_TEXT, tabNames, EMAIL_FROM), true);

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Ошибка отправки почты" + e.getMessage());
        }
    }

    private void removingFinishedTasks(Notification notification) {
        notificationRepository.deleteByIdAndTabName(notification.getId(), notification.getTabName());
    }
}