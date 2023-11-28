package edu.zhuravlev.telegrambusbot2.update_processing;

import edu.zhuravlev.telegrambusbot2.update_processing.events.BotCommandEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleUpdateDispatcher implements UpdateDispatcher {
    @Lazy
    @Autowired
    private Map<String, BotCommandEvent> allCommands;

    @Override
    public void dispatch(Update update) {
        if (update.getMessage().hasText()) {
            allCommands.get(update.getMessage().getText()).process(update);
        }
    }
}
