package edu.zhuravlev.telegrambusbot2.services.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class SimpleSendService implements SendService {
    @Autowired
    private AbsSender absSender;

    @Override
    public void send(BotApiMethodMessage botApiMethodMessage) {
        try {
            absSender.execute(botApiMethodMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
