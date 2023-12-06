package edu.zhuravlev.telegrambusbot2.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BotCommandsMeta {
    private static final String COMMAND_NOT_FOUND = "Bot command not found by mapping: %s";
    private Map<String, Pair<BotCommandEventHandler, BotCommand>> metadata;
    private List<BotCommand> cashCommands;

    public BotCommandEventHandler getCommandHandler(String botCommandName) {
         Pair<BotCommandEventHandler, BotCommand> pairMeta = metadata.get(botCommandName);

         if (pairMeta == null) {
             log.info(COMMAND_NOT_FOUND.formatted(botCommandName));
             return null;
         }

         return pairMeta.getFirst();
    }

    public BotCommand getBotCommand(String botCommandName) {
        Pair<BotCommandEventHandler, BotCommand> pairMeta = metadata.get(botCommandName);

        if (pairMeta == null) {
            log.info(COMMAND_NOT_FOUND.formatted(botCommandName));
            return null;
        }

        return pairMeta.getSecond();
    }

    public List<BotCommand> getAllBotCommands() {
        if (cashCommands == null) {
            cashCommands = metadata.values().stream().map(Pair::getSecond).toList();
        }

        return cashCommands;
    }
}
