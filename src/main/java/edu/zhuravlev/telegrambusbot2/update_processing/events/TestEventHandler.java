package edu.zhuravlev.telegrambusbot2.update_processing.events;

import edu.zhuravlev.telegrambusbot2.annotation.TelegramBotCommand;
import edu.zhuravlev.telegrambusbot2.common.BotCommandEventHandler;
import edu.zhuravlev.telegrambusbot2.services.send.SendService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;


@RequiredArgsConstructor
@TelegramBotCommand(commandName = "/test", commandDescription = "Тестовая команда")
public class TestEventHandler implements BotCommandEventHandler {
    private final SendService sendService;

    private List<BotCommand> commands;

    @Override
    public void process(Update update) {
        sendService.sendText(update.getMessage().getChatId().toString(), "Test");
        System.out.println(commands);
    }
}
