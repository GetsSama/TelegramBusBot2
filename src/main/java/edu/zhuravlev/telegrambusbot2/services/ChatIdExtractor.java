package edu.zhuravlev.telegrambusbot2.services;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatIdExtractor {
    public static String getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId().toString();
        }

        if (update.hasPollAnswer()) {
            return update.getPollAnswer().getVoterChat().getId().toString();
        }

        return null;
    }
}
