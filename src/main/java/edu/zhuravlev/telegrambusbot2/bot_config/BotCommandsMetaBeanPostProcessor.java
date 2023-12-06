package edu.zhuravlev.telegrambusbot2.bot_config;

import edu.zhuravlev.telegrambusbot2.annotation.TelegramBotCommand;
import edu.zhuravlev.telegrambusbot2.common.BotCommandEventHandler;
import edu.zhuravlev.telegrambusbot2.common.BotCommandsMeta;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.util.Pair;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class BotCommandsMetaBeanPostProcessor implements BeanPostProcessor {
    private static final String INCOMPATIBLE_ANNOTATION = "'@TelegramBotCommand' accessed only for BotCommandEventHandler implementation";
    private static final Field METADATA_FIELD = injectedField();

    private Map<String, Pair<BotCommandEventHandler, BotCommand>> collectMetadata = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);

        if (targetClass.isAnnotationPresent(TelegramBotCommand.class)) {
            if (!BotCommandEventHandler.class.isAssignableFrom(targetClass)) {
                log.error(INCOMPATIBLE_ANNOTATION);
                throw new IllegalStateException(INCOMPATIBLE_ANNOTATION + targetClass.getName());
            }

            TelegramBotCommand anno = targetClass.getAnnotation(TelegramBotCommand.class);
            BotCommand command = new BotCommand(anno.commandName(), anno.commandDescription());

            collectMetadata.put(anno.commandName(), Pair.of((BotCommandEventHandler) bean, command));
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);

        if (targetClass == BotCommandsMeta.class) {
            ReflectionUtils.setField(METADATA_FIELD, bean, collectMetadata);
        }

        return bean;
    }

    //TODO Hardcode field name
    @SneakyThrows
    private static Field injectedField() {
        return BotCommandsMeta.class.getDeclaredField("metadata");
    }
}
