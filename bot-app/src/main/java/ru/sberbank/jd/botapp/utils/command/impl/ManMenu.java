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
 * Команда услуг для мужчин.
 */
@Data
@NoArgsConstructor
public class ManMenu implements BaseCommand {
    @Override
    public SendMessage getMessage(Long chatId, Long userId) {

        StringBuilder message = new StringBuilder();
        message.append("Выберите услугу");
        SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(message.toString());


//        String[][] buttons = {{"Мужская стрижка"}, {"Детская стрижка"}, {"Коррекция бороды"},{"Меню"}};
//        ReplyKeyboardMarkup replyKeyboardMarkup = new Keyboard().getKeyboard(buttons);
//        result.setReplyMarkup(replyKeyboardMarkup);
        List<String> buttons = new ArrayList<>();
        buttons.add("Мужская стрижка");
        buttons.add("Детская стрижка");
        buttons.add("Коррекция бороды");
        InlineKeyboardMarkup inlineKeyboardMarkup = new Keyboard().getKeyboard(
                buttons,
                1,
                true);
        result.setReplyMarkup(inlineKeyboardMarkup);
        return result;

    }
}
