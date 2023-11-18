package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

/**
 * Команда текущие заказы.
 */
public class MyOrders extends AbstractCommandImpl implements Command {
    public MyOrders(){
        super();
        setCommandName("Мои записи");
        setCommandText("Ваши записи");
    }
    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }
}
