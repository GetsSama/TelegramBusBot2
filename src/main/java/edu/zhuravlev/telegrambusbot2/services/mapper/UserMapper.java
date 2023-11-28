package edu.zhuravlev.telegrambusbot2.services.mapper;


import edu.zhuravlev.telegrambusbot2.model.TelegramUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "firstName", target = "name")
    TelegramUser telegramUserFromUser(User user);
}
