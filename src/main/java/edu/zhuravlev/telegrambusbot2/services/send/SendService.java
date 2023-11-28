package edu.zhuravlev.telegrambusbot2.services.send;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface SendService {
    void send(BotApiMethodMessage botApiMethodMessage);

    default void sendText(String chatId, String text) {
        send(SendMessage.builder().chatId(chatId).text(text).build());
    }
}
