package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

/**
 * Команда выбора времени.
 */
public class ChooseDate extends AbstractCommandImpl implements Command {

    public ChooseDate() {
        super();
        setCommandText("Выберите доступное время");
    }

    public ChooseDate(String date) {
        this();
        setCommandName(date);
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2), true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }
}
