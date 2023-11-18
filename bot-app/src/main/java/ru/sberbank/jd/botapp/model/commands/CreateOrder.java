package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

/**
 * Команда записаться.
 */
public class CreateOrder extends AbstractCommandImpl implements Command {

    public CreateOrder(){
        super();
        setCommandName("Записаться");
        setCommandText("Выберите мастера");
    }
    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2)));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }
}
