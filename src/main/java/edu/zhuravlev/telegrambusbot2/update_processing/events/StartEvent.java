package edu.zhuravlev.telegrambusbot2.update_processing.events;

import edu.zhuravlev.telegrambusbot2.TestClass;
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
    private final TestClass testClass;
    private int counter;
    @Override
    public void process(Update update) {
        sender.sendText(update.getMessage().getChatId().toString(), "Start command");

        switch (counter) {
            case 0 -> testClass.save();
            case 1 -> testClass.delete();
        }

        counter++;
    }
}
