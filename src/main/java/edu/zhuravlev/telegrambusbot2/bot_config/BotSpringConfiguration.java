package edu.zhuravlev.telegrambusbot2.bot_config;


import busparser.BusParser;
import busparser.DefaultBusParser;
import edu.zhuravlev.telegrambusbot2.common.BotCommandsMeta;
import edu.zhuravlev.telegrambusbot2.update_processing.UpdateDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;


@Slf4j
@Configuration("botConfig")
@ConfigurationPropertiesScan
public class BotSpringConfiguration {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BotConfigurationProperties botProperties;
    @Autowired
    private UpdateDispatcher updateDispatcher;
    @Autowired
    private BotCommandsMeta meta;
    private List<BotCommand> allCommands;

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

        return configurableBot;
    }

    @Bean
    public BusParser busParser() {
        return DefaultBusParser.getInstance();
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        TelegramLongPollingBot busBot = context.getBean(TelegramLongPollingBot.class);

        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(busBot);
            log.info("Register bot: " + busBot.getBotUsername());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }

        SetMyCommands myCommands = new SetMyCommands();
        List<BotCommand> commands = meta.getAllBotCommands();

        myCommands.setCommands(commands);


        try{
            busBot.execute(myCommands);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
