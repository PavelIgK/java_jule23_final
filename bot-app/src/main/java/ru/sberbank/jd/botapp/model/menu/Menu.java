package ru.sberbank.jd.botapp.model.menu;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.commands.AbstractCommandImpl;
import ru.sberbank.jd.botapp.model.commands.GoBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Menu {

    public static List<List<InlineKeyboardButton>> addBackButton(List<List<InlineKeyboardButton>> menu) {
        GoBack goBack = new GoBack();

        List<InlineKeyboardButton> bottomElem = new ArrayList<>();
        bottomElem.add(InlineKeyboardButton.builder()
                .text(goBack.getCommandName())
                .callbackData(goBack.getClass().getName())
                .build());

        menu.add(bottomElem);

        return menu;
    }

    public static List<List<InlineKeyboardButton>> createMenu(List<AbstractCommandImpl> commands, int btnInLine) {

        List<InlineKeyboardButton> tgButtons =
                commands.stream().map(e -> InlineKeyboardButton.builder()
                                .text(e.getCommandName())
                                .callbackData(e.getClass().getName())
                                .build())
                        .toList();

        AtomicInteger counter = new AtomicInteger();
        Map<Integer, List<InlineKeyboardButton>> mapOfChunks = tgButtons.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / btnInLine));

        return new ArrayList<>(mapOfChunks.values());
    }

    public static BotApiMethod getKeyboard(ChatInfo chatInfo, SendMessage sendMessage, List<List<InlineKeyboardButton>> menu) {
        BotApiMethod callback = null;
        if (chatInfo.getMessageId() != null) {
            callback = refreshKeyboard(chatInfo, sendMessage, menu, true);
        } else {
            callback = sendKeyboard(chatInfo, sendMessage, menu);
        }
        return callback;
    }

    public static EditMessageText refreshKeyboard(ChatInfo chatInfo, SendMessage sendMessage, List<List<InlineKeyboardButton>> menu, boolean addBackBtn) {

        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(chatInfo.getChatId())
                .messageId(chatInfo.getMessageId())
                .text(sendMessage.getText())
                .replyMarkup((InlineKeyboardMarkup) sendMessage.getReplyMarkup())
                .build();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menu);
        if (addBackBtn){
            inlineKeyboardMarkup.setKeyboard(Menu.addBackButton(menu));
        }
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        return editMessageText;
    }

    public static SendMessage sendKeyboard(ChatInfo chatInfo, SendMessage sendMessage, List<List<InlineKeyboardButton>> menu) {

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(menu)
                .build();

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }


}
