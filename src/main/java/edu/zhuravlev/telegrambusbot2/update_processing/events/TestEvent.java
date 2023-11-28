package edu.zhuravlev.telegrambusbot2.update_processing.events;

import edu.zhuravlev.telegrambusbot2.services.send.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/test")
@RequiredArgsConstructor
public class TestEvent implements BotCommandEvent {
    private final SendService sendService;

    @Override
    public void process(Update update) {
        sendService.sendText(update.getMessage().getChatId().toString(), "Test");
    }
}
