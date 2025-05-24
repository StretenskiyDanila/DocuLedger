package org.example.notificationapp.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Getter
public class BotConfig {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotConfig() {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public DefaultBotOptions botOptions() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setMaxThreads(10);
        return options;
    }

//    @Bean
//    public TelegramBot myBot(DefaultBotOptions options,
//                             BotConfig telegramBotConfig,
//                             TelegramBotsApi telegramBotsApi,
//                             NotificationService notificationService,
//                             NotificationRepository notificationRepository) {
//        return new TelegramBot(options,
//                telegramBotConfig,
//                telegramBotsApi,
//                notificationService,
//                notificationRepository);
//    }

}
