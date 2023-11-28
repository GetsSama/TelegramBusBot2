package edu.zhuravlev.telegrambusbot2.services.user;

import edu.zhuravlev.telegrambusbot2.model.TelegramUser;

import java.util.Optional;

public interface TelegramUserService {
    Optional<TelegramUser> getTelegramUserById(String id);
    Optional<TelegramUser> getTelegramUserByChatId(String chatId);
    void saveTelegramUser(TelegramUser user);
}
