package org.example.notificationapp.service;

import org.example.notificationapp.model.Notification;

public interface NotificationService {

    void addNotification(Notification notification);
    void changeNotification(Notification notification);

}
