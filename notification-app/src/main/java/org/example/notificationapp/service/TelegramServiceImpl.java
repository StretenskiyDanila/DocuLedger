package org.example.notificationapp.service;

import org.example.notificationapp.controller.TelegramBot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class TelegramServiceImpl implements TelegramService {

    private TelegramBot telegramBot;

    @SneakyThrows
    @Override
    public void sendMessage(String chatId, String message) {
        telegramBot.executeAsync(new SendMessage(chatId, message));
    }

    @Autowired
    public void setTelegramBot(@Lazy TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

}
