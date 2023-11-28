package edu.zhuravlev.telegrambusbot2.repository;


import edu.zhuravlev.telegrambusbot2.model.TelegramUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<TelegramUser, String> {
    Optional<TelegramUser> findTelegramUserByChatId(String chatId);
}
