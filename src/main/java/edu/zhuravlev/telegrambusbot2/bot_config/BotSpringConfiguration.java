package edu.zhuravlev.telegrambusbot2.bot_config;


import busparser.BusParser;
import busparser.DefaultBusParser;
import edu.zhuravlev.telegrambusbot2.update_processing.UpdateDispatcher;
import edu.zhuravlev.telegrambusbot2.update_processing.events.BotCommandEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


@Slf4j
@Configuration
@ConfigurationPropertiesScan
public class BotSpringConfiguration {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BotConfigurationProperties botProperties;
    @Autowired
    private UpdateDispatcher updateDispatcher;

    @Bean
    public TelegramLongPollingBot busBot() {
        TelegramLongPollingBot configurableBot = new TelegramLongPollingBot(botProperties.getToken()) {
            @Override
            public void onUpdateReceived(Update update) {
                updateDispatcher.dispatch(update);
            }

            @Override
            public String getBotUsername() {
                return botProperties.getName();
            }
        };

        registerBot(configurableBot);

        return configurableBot;
    }

    @Bean
    public BusParser busParser() {
        return DefaultBusParser.getInstance();
    }

    @Bean
    @Lazy
    public Map<String, BotCommandEvent> allCommands() {
        String[] beansNames = context.getBeanDefinitionNames();

        Stream.of(beansNames).forEach(System.out::println);

        return new HashMap<>();
    }

    private void registerBot(TelegramLongPollingBot bot) {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(bot);
            log.info("Register bot: " + bot.getBotUsername());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
