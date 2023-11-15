package ru.sberbank.jd.botapp.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Keyboard {

    /**
     * Это клавиатура на весь чат.
     *
     * @param buttons двумерный массов с текстом для кнопок.
     *                {}{"Кнопка1", "Кнопка2"},{"Кнопка3"}}
     *                Получится:
     *                Кнопка1 Кнопка2
     *                   Кнопка3
     *
     * @return ReplyKeyboardMarkup
     */
    public ReplyKeyboardMarkup getKeyboard(String[][] buttons) {
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

    /**
     * Это клавиатура для конкретного сообщения.
     *
     * @param commandCatalog Список кнопок
     * @param buttonInLine количество кнопок в одном ряду
     * @param addMenuButton добавлять ли кнопку меню
     * @return ReplyKeyboardMarkup
     */
    public InlineKeyboardMarkup getKeyboardFromCommand(List<CommandCatalog> commandCatalog, Integer buttonInLine, boolean addMenuButton) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        int countInLine = 0;
        List<InlineKeyboardButton> lineButtons = new ArrayList<>();
        for (CommandCatalog command : commandCatalog) {
            if (countInLine == buttonInLine) {
                rows.add(lineButtons);
                lineButtons = new ArrayList<>();
                countInLine = 0;
            }
            lineButtons.add(createButton(command.getValue(), command.getValue()));
            countInLine++;
        }
        rows.add(lineButtons); //добавляем кнопки последнего ряда оценок

        if (!addMenuButton) {
            inlineKeyboardMarkup.setKeyboard(rows);
            return inlineKeyboardMarkup;
        }

        lineButtons = new ArrayList<>();
        lineButtons.add(createButton("Меню", "Меню"));
        rows.add(lineButtons);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    /**
     * Это клавиатура для конкретного сообщения.
     *
     * @param commandList Список кнопок
     * @param buttonInLine количество кнопок в одном ряду
     * @param addMenuButton добавлять ли кнопку меню
     * @return ReplyKeyboardMarkup
     */
    public InlineKeyboardMarkup getKeyboard(List<String> commandList, Integer buttonInLine, boolean addMenuButton) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        int countInLine = 0;
        List<InlineKeyboardButton> lineButtons = new ArrayList<>();
        for (String command : commandList) {
            if (countInLine == buttonInLine) {
                rows.add(lineButtons);
                lineButtons = new ArrayList<>();
                countInLine = 0;
            }
            lineButtons.add(createButton(command, command));
            countInLine++;
        }
        rows.add(lineButtons); //добавляем кнопки последнего ряда оценок

        if (!addMenuButton) {
            inlineKeyboardMarkup.setKeyboard(rows);
            return inlineKeyboardMarkup;
        }

        lineButtons = new ArrayList<>();
        lineButtons.add(createButton("Меню", "Меню"));
        rows.add(lineButtons);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardButton createButton(String text, String command) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text.trim());
        button.setCallbackData(command.trim());

        return button;
    }
}
