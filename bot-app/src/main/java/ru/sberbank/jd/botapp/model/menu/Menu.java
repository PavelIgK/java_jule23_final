package ru.sberbank.jd.botapp.model.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.sberbank.jd.botapp.model.CallbackData;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.commands.AbstractCommandImpl;
import ru.sberbank.jd.botapp.model.commands.GoBack;
import ru.sberbank.jd.botapp.model.commands.ScrollBack;
import ru.sberbank.jd.botapp.model.commands.ScrollForward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import ru.sberbank.jd.botapp.utils.CommandCatalog;

@Data
@NoArgsConstructor
public class Menu {

    private static List<List<InlineKeyboardButton>> addPagination(List<List<InlineKeyboardButton>> menu) {

        List<InlineKeyboardButton> bottomElem = new ArrayList<>();

        ScrollBack scrollBack = new ScrollBack();
        ScrollForward scrollForward = new ScrollForward();

        String scrollBackCommand = CommandCatalog.SCROLLBACK.toString();
        String scrollForwardCommand = CommandCatalog.SCROLLFORWARD.toString();

        bottomElem.add(InlineKeyboardButton.builder()
                .text(scrollBack.getCommandName())
                .callbackData(scrollBackCommand)
                .build());
        bottomElem.add(InlineKeyboardButton.builder()
                .text(scrollForward.getCommandName())
                .callbackData(scrollForwardCommand)
                .build());
        menu.add(bottomElem);

        return menu;
    }

    private static List<List<InlineKeyboardButton>> addBackButton(List<List<InlineKeyboardButton>> menu) {
        GoBack goBack = new GoBack();

        List<InlineKeyboardButton> bottomElem = new ArrayList<>();
        String command = CommandCatalog.GOBACK.toString();
        bottomElem.add(InlineKeyboardButton.builder()
                .text(goBack.getCommandName())
                .callbackData(command)
                .build());

        menu.add(bottomElem);

        return menu;
    }

    public static List<List<InlineKeyboardButton>> createMenu(List<AbstractCommandImpl> commands, int btnInLine) {

        ObjectMapper objectMapper = new ObjectMapper();

        List<InlineKeyboardButton> tgButtons =
                commands.stream().map(e -> {
                            String data = e.getDataToSend() == null ? "" : e.getDataToSend();
                            String className = e.getClass().getName();
                            String commandData = CommandCatalog.valueOfClassName(className) + ";" + data;
                            return InlineKeyboardButton.builder()
                                    .text(e.getCommandName())
                                    .callbackData(commandData)
                                    .build();
                        })
                        .toList();

        AtomicInteger counter = new AtomicInteger();
        Map<Integer, List<InlineKeyboardButton>> mapOfChunks = tgButtons.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / btnInLine));

        return new ArrayList<>(mapOfChunks.values());
    }

    public static BotApiMethod getKeyboard(ChatInfo chatInfo, SendMessage sendMessage,
            List<List<InlineKeyboardButton>> menu, boolean addBackBtn, boolean pagination) {
        BotApiMethod callback = null;
        if (chatInfo.getMessageId() != null) {
            callback = refreshKeyboard(chatInfo, sendMessage, menu, addBackBtn, pagination);
        } else {
            callback = sendKeyboard(chatInfo, sendMessage, menu);
        }
        return callback;
    }

    private static EditMessageText refreshKeyboard(ChatInfo chatInfo, SendMessage sendMessage,
            List<List<InlineKeyboardButton>> menu, boolean addBackBtn, boolean pagination) {

        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(chatInfo.getChatId())
                .messageId(chatInfo.getMessageId())
                .text(sendMessage.getText())
                .replyMarkup((InlineKeyboardMarkup) sendMessage.getReplyMarkup())
                .build();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menu);
        if (pagination) {
            inlineKeyboardMarkup.setKeyboard(Menu.addPagination(menu));
        }
        if (addBackBtn) {
            inlineKeyboardMarkup.setKeyboard(Menu.addBackButton(menu));
        }
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        return editMessageText;
    }

    private static SendMessage sendKeyboard(ChatInfo chatInfo, SendMessage sendMessage,
            List<List<InlineKeyboardButton>> menu) {

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(menu)
                .build();

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }


}
