package edu.zhuravlev.telegrambusbot2.update_processing.events;

import edu.zhuravlev.telegrambusbot2.model.TelegramUser;
import edu.zhuravlev.telegrambusbot2.services.mapper.UserMapper;
import edu.zhuravlev.telegrambusbot2.services.send.SendService;
import edu.zhuravlev.telegrambusbot2.services.user.TelegramUserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;


@Slf4j
@Component("/start")
@RequiredArgsConstructor
public class StartEvent implements BotCommandEvent {
    private final SendService sendService;
    private final TelegramUserService userService;
    private final UserMapper userMapper;

    private String message;

    @PostConstruct
    private void prepareMessage() {
        message = "Это бот для отслеживания автобусов.\nВот список моих комманд:";
    }

    @Override
    public void process(Update update) {
        User user = update.getMessage().getFrom();
        String chatId = update.getMessage().getChatId().toString();

        if(userService.getTelegramUserById(user.getId().toString()).isEmpty()) {
            TelegramUser newUser = userMapper.telegramUserFromUser(user);

            newUser.setChatId(chatId);
            userService.saveTelegramUser(newUser);
            sendService.sendText(chatId, message);
        }
    }
}
