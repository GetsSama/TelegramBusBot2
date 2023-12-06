package edu.zhuravlev.telegrambusbot2.annotation;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Scope("singleton")
public @interface TelegramBotCommand {
    String commandName();
    String commandDescription();
}
