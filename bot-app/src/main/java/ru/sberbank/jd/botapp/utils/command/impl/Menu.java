package ru.sberbank.jd.botapp.utils.command.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.sberbank.jd.botapp.utils.Keyboard;
import ru.sberbank.jd.botapp.utils.command.BaseCommand;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Menu implements BaseCommand {

    @Override
    public SendMessage getMessage(Long chatId, String name) {
        StringBuilder message = new StringBuilder();
        message.append("Что пожелаешь мой господин");

        SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(message.toString());

        String[][] buttons = {{"Записаться", "Мои записи"}, {"Информация"}};
        ReplyKeyboardMarkup replyKeyboardMarkup = Keyboard.getKeyboard(buttons);
        result.setReplyMarkup(replyKeyboardMarkup);
        return result;
    }
}
