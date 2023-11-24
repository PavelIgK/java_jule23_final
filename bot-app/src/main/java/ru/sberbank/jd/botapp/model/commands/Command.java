package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sberbank.jd.botapp.model.ChatInfo;

/**
 * Интерфейс для реализации команд.
 *
 */
public interface Command {

    ChatInfo execute (ChatInfo chatInfo);
    BotApiMethod execute (Update update);

}
