package edu.zhuravlev.telegrambusbot2.services.send;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
@RequiredArgsConstructor
public class SimpleSender implements Sender {
    private final AbsSender absSender;

    @Override
    public void send(BotApiMethodMessage botApiMethodMessage) {
        try {
            absSender.execute(botApiMethodMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
