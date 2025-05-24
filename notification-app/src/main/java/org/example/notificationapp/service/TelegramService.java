package org.example.notificationapp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

public interface TelegramService {

    void sendMessage(String chatId, String message);

    void sendFile(SendDocument document);

}
