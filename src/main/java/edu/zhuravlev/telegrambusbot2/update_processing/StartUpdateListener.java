package edu.zhuravlev.telegrambusbot2.update_processing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
@Component
@RequiredArgsConstructor
public class StartUpdateListener implements UpdateListener {
    private final AbsSender sender;

    @Override
    public void process(Update update) {
        SendMessage message = new SendMessage();

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Привет! Бот жив!");

        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
