package edu.zhuravlev.telegrambusbot2.update_processing;

import edu.zhuravlev.telegrambusbot2.common.BotCommandsMeta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleUpdateDispatcher implements UpdateDispatcher {
    @Autowired
    private BotCommandsMeta commandsMeta;

    @Override
    public void dispatch(Update update) {
        if (update.getMessage().hasText()) {
            commandsMeta.getCommandHandler(update.getMessage().getText()).process(update);
        }
    }
}
