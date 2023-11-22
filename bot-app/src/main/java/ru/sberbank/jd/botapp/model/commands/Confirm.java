package ru.sberbank.jd.botapp.model.commands;

import java.util.ArrayList;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

/**
 * Команда выбора даты.
 */

public class Confirm extends AbstractCommandImpl implements Command {

    private String id;
    public Confirm() {
        super();
        setCommandText("Ваш заказ");

    }

    public Confirm(String choiseTime) {
        this();
        setCommandName(choiseTime);
    }

    public Confirm(String choiseTime, String choiseTimeData) {
        this();
        setCommandName(choiseTime);
        setDataToSend(choiseTimeData);
    }


    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        String choiseTime = chatInfo.getCallbackData().getData();

        commands = new ArrayList<>();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }

}
