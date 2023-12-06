package edu.zhuravlev.telegrambusbot2.common;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommandEventHandler {
    void process(Update update);
}
