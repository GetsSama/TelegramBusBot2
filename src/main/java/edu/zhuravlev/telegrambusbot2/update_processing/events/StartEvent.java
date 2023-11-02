package edu.zhuravlev.telegrambusbot2.update_processing.events;

import edu.zhuravlev.telegrambusbot2.services.send.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Component("/start")
@RequiredArgsConstructor
public class StartEvent implements BotCommandEvent {
    private final Sender sender;

    @Override
    public void process(Update update) {
        sender.sendText(update.getMessage().getChatId().toString(), "Start command");
    }
}
