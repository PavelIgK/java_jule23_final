package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

import java.util.ArrayList;

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

        commands = new ArrayList<>();
        commands.add(new ChoosePerformer("Причесов Игорь"));
        commands.add(new ChoosePerformer("Бритвин Антон"));
        commands.add(new ChoosePerformer("Расчесок Влажек"));
        commands.add(new ChoosePerformer("Усов Длин"));

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }
}
