package edu.zhuravlev.telegrambusbot2.update_processing.events;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommandEvent {
    void process(Update update);
}
