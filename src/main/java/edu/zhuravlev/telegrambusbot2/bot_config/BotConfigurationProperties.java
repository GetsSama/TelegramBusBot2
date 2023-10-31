package edu.zhuravlev.telegrambusbot2.bot_config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "bot.config")
public class BotConfig {
    private final String name;
    private final String token;
}
