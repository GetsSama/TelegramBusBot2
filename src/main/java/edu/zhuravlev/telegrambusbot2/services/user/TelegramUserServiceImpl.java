package edu.zhuravlev.telegrambusbot2.services.user;

import edu.zhuravlev.telegrambusbot2.model.TelegramUser;
import edu.zhuravlev.telegrambusbot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final UserRepository userRepository;

    @Override
    public Optional<TelegramUser> getTelegramUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<TelegramUser> getTelegramUserByChatId(String chatId) {
        return userRepository.findTelegramUserByChatId(chatId);
    }

    @Override
    public void saveTelegramUser(TelegramUser user) {
        userRepository.save(user);
    }
}
