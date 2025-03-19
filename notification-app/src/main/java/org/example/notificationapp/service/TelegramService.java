package org.example.notificationapp.service;

public interface TelegramService {

    void sendMessage(String chatId, String message);

}
