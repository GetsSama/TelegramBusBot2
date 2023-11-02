package edu.zhuravlev.telegrambusbot2.update_processing;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateDispatcher {
    void dispatch(Update update);
}
