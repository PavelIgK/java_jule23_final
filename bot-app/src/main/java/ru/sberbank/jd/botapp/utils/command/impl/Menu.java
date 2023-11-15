package ru.sberbank.jd.botapp.utils.command.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sberbank.jd.botapp.utils.CommandCatalog;
import ru.sberbank.jd.botapp.utils.Keyboard;
import ru.sberbank.jd.botapp.utils.command.BaseCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда меню.
 */
@Data
@NoArgsConstructor
public class Menu implements BaseCommand {

    @Override
    public SendMessage getMessage(Long chatId, Long userId) {
        StringBuilder message = new StringBuilder();
        message.append("Что пожелаешь мой господин");

        SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(message.toString());

        //String[][] buttons = {{"Записаться", "Мои записи"}, {"Информация"}};
        //ReplyKeyboardMarkup replyKeyboardMarkup = new Keyboard().getKeyboard(buttons);
        //result.setReplyMarkup(replyKeyboardMarkup);
        List<String> buttons = new ArrayList<>();
        buttons.add("Записаться");
        buttons.add("Мои записи");
        buttons.add("Информация");
        InlineKeyboardMarkup inlineKeyboardMarkup = new Keyboard().getKeyboard(
                buttons,
                2,
                false);
        result.setReplyMarkup(inlineKeyboardMarkup);
        return result;
    }
}
