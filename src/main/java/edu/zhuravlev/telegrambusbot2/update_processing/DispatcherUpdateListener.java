package edu.zhuravlev.telegrambusbot2.update_processing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@Primary
@RequiredArgsConstructor
public class DispatcherUpdateListener implements UpdateListener {
    @Autowired
    private ApplicationContext context;

    @Override
    public void process(Update update) {
        if (update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
              context.getBean(StartUpdateListener.class).process(update);
            }
        }
    }
}
