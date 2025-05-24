package org.example.notificationapp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationapp.controller.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    private final TelegramBot telegramBot;

    @SneakyThrows
    @Override
    public void sendMessage(String chatId, String message) {
        telegramBot.executeAsync(new SendMessage(chatId, message));
    }

    @Override
    public void sendFile(SendDocument document) {
        try {
            telegramBot.execute(document);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки файла через Telegram. Error: " + e.getMessage());
        }
    }

}
