package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

/**
 * Команда информация.
 */
public class Information extends AbstractCommandImpl implements Command {
    public Information() {
        super();
        setCommandName("Информация");
        setCommandText("Мы находимся по адресу:\n" +
                "WWW Ленинград:\n" +
                "СПБ . РУ:\n");

        commands.add(new Location());
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
