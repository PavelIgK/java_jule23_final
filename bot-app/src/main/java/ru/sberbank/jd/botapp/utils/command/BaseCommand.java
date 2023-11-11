package ru.sberbank.jd.botapp.utils.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BaseCommand {
    SendMessage getMessage(Long chatId, String name);
}
