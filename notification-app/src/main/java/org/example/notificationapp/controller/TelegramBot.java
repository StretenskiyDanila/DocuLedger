package org.example.notificationapp.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.notificationapp.config.TelegramBotConfig;
import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.repository.NotificationRepository;
import org.example.notificationapp.service.NotificationService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;
    private final TelegramBotsApi telegramBotsApi;

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    private final static String EMPTY_STRING = "";

    @SneakyThrows
    @PostConstruct
    public void init() {
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userName;
        Long chatId;
        String messageText = "";
        if (update.hasCallbackQuery()) {
            userName = update.getCallbackQuery().getFrom().getUserName();
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else if (update.hasMessage()) {
            userName = update.getMessage().getFrom().getUserName();
            chatId = update.getMessage().getChatId();
            messageText = update.getMessage().getText();
        } else {
            return;
        }
        if (messageText.equals("/register")) {
            if (!notificationRepository.existsByUserName(userName)) {
                notificationService.changeNotification(Notification.builder()
                        .userName(userName)
                        .userId(chatId)
                        .state(Notification.StateEnum.NONE)
                        .tabName(EMPTY_STRING)
                        .build());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotName();
    }

}
