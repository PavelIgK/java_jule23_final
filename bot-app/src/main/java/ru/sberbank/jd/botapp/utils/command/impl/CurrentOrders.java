package ru.sberbank.jd.botapp.utils.command.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.sberbank.jd.botapp.utils.Keyboard;
import ru.sberbank.jd.botapp.utils.command.BaseCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда текущие заказы.
 */
@Data
@NoArgsConstructor
public class CurrentOrders implements BaseCommand {
    @Override
    public SendMessage getMessage(Long chatId, String name) {
        StringBuilder message = new StringBuilder();
        message.append("1. Мужская стрижка. 31.12.2023 в 10:00. Стоимость 1000 рублей.\n");
        message.append("2. Женская стрижка. 31.12.2023 в 10:00. Стоимость 1500 рублей.\n");
        message.append("3. Детская стрижка. 31.12.2023 в 10:00. Стоимость 500 рублей.\n");
        SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(message.toString());


//        String[][] buttons = {{"Мужчина", "Женщина"}, {"Меню"}};
//        ReplyKeyboardMarkup replyKeyboardMarkup = new Keyboard().getKeyboard(buttons);
//        result.setReplyMarkup(replyKeyboardMarkup);
        List<String> buttons = new ArrayList<>();
        buttons.add("1. Мужская стрижка");
        buttons.add("2. Женская стрижка");
        buttons.add("3. Детская стрижка");
        InlineKeyboardMarkup inlineKeyboardMarkup = new Keyboard().getKeyboard(
                buttons,
                1,
                true);
        result.setReplyMarkup(inlineKeyboardMarkup);
        return result;
    }
}
