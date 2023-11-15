package ru.sberbank.jd.botapp.utils.command.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.utils.CommandCatalog;
import ru.sberbank.jd.botapp.utils.command.BaseCommand;

/**
 * Команда информация.
 */
@Data
@NoArgsConstructor
public class Information implements BaseCommand {
    @Override
    public SendMessage getMessage(Long chatId, Long userId) {

        StringBuilder message = new StringBuilder();
        message.append("Мы находимся по адресу:\n");
        message.append("WWW Ленинград:\n");
        message.append("СПБ . РУ:\n");

        SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(message.toString());

        return result;
    }
}
