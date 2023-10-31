package edu.zhuravlev.telegrambusbot2.bot_config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "bot.config")
public class BotConfigurationProperties {
    private final String name;
    private final String token;
}
