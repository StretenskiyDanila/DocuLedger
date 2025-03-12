package org.example.notificationapp.service;

import lombok.RequiredArgsConstructor;
import org.example.notificationapp.model.Notification;
import org.example.notificationapp.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void changeNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    //TODO: realize send notification on Scheduler
    private void sendNotification(Notification notification) {

    }

    private void removingFinishedTasks(Notification notification) {
        notificationRepository.deleteByUserId(notification.getUserId());
    }

}