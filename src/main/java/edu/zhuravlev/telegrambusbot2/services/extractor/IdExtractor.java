package edu.zhuravlev.telegrambusbot2.services.extractor;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IdExtractor {
    String getId(Update update);
}
