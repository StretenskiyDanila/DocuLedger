package org.example.notificationapp.controller;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationapp.config.BotConfig;
import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.entity.enums.NotificationChannel;
import org.example.notificationapp.entity.enums.StateEnum;
import org.example.notificationapp.repository.NotificationRepository;
import org.example.notificationapp.service.NotificationService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig telegramBotConfig;
    private final TelegramBotsApi telegramBotsApi;

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    private final static String EMPTY_STRING = "";

    public TelegramBot(BotConfig telegramBotConfig,
                       TelegramBotsApi telegramBotsApi,
                       NotificationService notificationService,
                       NotificationRepository notificationRepository) {
        super(telegramBotConfig.getBotToken());
        this.telegramBotConfig = telegramBotConfig;
        this.telegramBotsApi = telegramBotsApi;
        this.notificationService = notificationService;
        this.notificationRepository = notificationRepository;
    }

    @SneakyThrows
    @PostConstruct
    public void init() {
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Start telegram receive");
        String userName;
        Long chatId;
        if (update.hasCallbackQuery()) {
            userName = update.getCallbackQuery().getFrom().getUserName();
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else if (update.hasMessage()) {
            userName = update.getMessage().getFrom().getUserName();
            chatId = update.getMessage().getChatId();
        } else {
            return;
        }

        Notification notification = Notification.builder()
                .userName(userName)
                .userId(chatId)
                .state(StateEnum.NONE)
                .channel(NotificationChannel.TELEGRAM)
                .tabName(EMPTY_STRING)
                .enable(false)
                .build();
        if (!notificationRepository.existsByUserName(userName)) {
            notificationService.changeNotification(notification);
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotName();
    }

}
