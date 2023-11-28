package edu.zhuravlev.telegrambusbot2.services.repository;


import edu.zhuravlev.telegrambusbot2.model.TelegramUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TelegramUser, String> {
}
