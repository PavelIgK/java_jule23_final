package ru.sberbank.jd.botapp.utils.command.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.sberbank.jd.botapp.utils.Keyboard;
import ru.sberbank.jd.botapp.utils.command.BaseCommand;

@Data
@NoArgsConstructor
public class ChoiseSex implements BaseCommand {
    @Override
    public SendMessage getMessage(Long chatId, String name) {
        StringBuilder message = new StringBuilder();
        message.append("Кого записываем?");
        SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(message.toString());


        String[][] buttons = {{"Мужчина", "Женщина"}, {"Меню"}};
        ReplyKeyboardMarkup replyKeyboardMarkup = Keyboard.getKeyboard(buttons);
        result.setReplyMarkup(replyKeyboardMarkup);
        return result;
    }
}
