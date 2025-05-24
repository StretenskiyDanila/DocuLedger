package org.example.notificationapp.service;

import org.example.notificationapp.entity.Notification;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

import java.io.File;

public interface NotificationService {

    void changeNotification(Notification notification);

    void changeNotificationEnable(String findParameter, String parameter, Boolean enable);

    void deleteChannel(String findParameter, String parameter);

    SendDocument getFileForBot(String userName, File file);

    void sendFileMail(String to, File file);

}
