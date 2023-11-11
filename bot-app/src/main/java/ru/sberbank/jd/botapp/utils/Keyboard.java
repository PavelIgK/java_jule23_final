package ru.sberbank.jd.botapp.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    public static ReplyKeyboardMarkup getKeyboard(String[][] buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //параметры клавиутуры
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(false);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        //добавляем "клавиатуру"

        List<KeyboardRow> rows = new ArrayList<>();
        for (String[] buttonLine : buttons) {
            List<KeyboardButton> buttonsInLine = new ArrayList<>();
            for (String button : buttonLine) {
                buttonsInLine.add(new KeyboardButton(button));
            }
            rows.add(new KeyboardRow(buttonsInLine));
        }
        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }
}
