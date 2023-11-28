package edu.zhuravlev.telegrambusbot2.services.extractor;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ChatIdExtractor implements IdExtractor {
    public String getId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId().toString();
        }

        if (update.hasPollAnswer()) {
            return update.getPollAnswer().getVoterChat().getId().toString();
        }

        return null;
    }
}
