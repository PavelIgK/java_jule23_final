package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

import java.util.ArrayList;

/**
 * Главное меню.
 */
public class MainMenu extends AbstractCommandImpl implements Command {

    public MainMenu() {
        super();
        setCommandName("Главное меню");
        setCommandText("Вас приветствует барбершоп 'Господские стрижки'");

        commands.add(new CreateOrder());
        commands.add(new MyOrders());
        commands.add(new Information());
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        if (chatInfo.getMessageId() != null) {
            chatInfo.setCallbackMsg(Menu.refreshKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),false));
        } else {
            chatInfo.setCallbackMsg(Menu.sendKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2)));
        }

        ArrayList<AbstractCommandImpl> newMenuCache = new ArrayList<>();
        newMenuCache.add(this);
        chatInfo.setMenuCache(newMenuCache);

        return chatInfo;
    }

}
