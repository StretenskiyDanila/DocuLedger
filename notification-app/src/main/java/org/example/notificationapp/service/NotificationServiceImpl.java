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

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final JavaMailSender mailSender;

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

    @Override
    public void changeNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Scheduled(cron = "0 27 11 * * *")
    @Transactional
    protected void sendNotification() {
        Map<String,String> notificationsData = notificationRepository.findAllByState(Notification.StateEnum.RUNNING)
                .stream()
                .collect(Collectors.groupingBy(
                        Notification::getUserMail,
                        Collectors.mapping(
                                Notification::getTabName,
                                Collectors.joining(", "))));

        notificationsData.entrySet()
                .forEach(this::sendMail);

        notificationRepository.findAllByState(Notification.StateEnum.FINISHED)
                .forEach(this::removingFinishedTasks);
    }

    private void removingFinishedTasks(Notification notification) {
        notificationRepository.deleteByIdAndTabName(notification.getId(), notification.getTabName());
    }

    private void sendMail(Map.Entry<String, String> user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setSubject("Напоминание оформления документов");
            helper.setFrom(EMAIL_FROM);
            helper.setTo(user.getKey());

            helper.setText(String.format(NOTIFICATION_TEXT, user.getValue(), EMAIL_FROM), true);

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Ошибка отправки почты" + e.getMessage());
        }
    }
}