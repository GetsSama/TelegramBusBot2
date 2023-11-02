package edu.zhuravlev.telegrambusbot2.update_processing;

import edu.zhuravlev.telegrambusbot2.update_processing.events.StartEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleUpdateDispatcher implements UpdateDispatcher {
    private final ApplicationContext context;

    @Override
    public void dispatch(Update update) {
        if (update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
                context.getBean(StartEvent.class).process(update);
            }
        }
    }
}
