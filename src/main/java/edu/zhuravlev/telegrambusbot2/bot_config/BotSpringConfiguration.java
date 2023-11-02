package edu.zhuravlev.telegrambusbot2.bot_config;


import edu.zhuravlev.telegrambusbot2.update_processing.UpdateDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Slf4j
@Configuration
@ConfigurationPropertiesScan
public class BotSpringConfiguration {
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

    private void registerBot(TelegramLongPollingBot bot) {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(bot);
            log.info("Register bot: " + bot.getBotUsername());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
