package ru.sberbank.jd.botapp.model.commands;

import lombok.experimental.SuperBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда записаться.
 */

public class ChoosePerformer extends AbstractCommandImpl implements Command {

    public ChoosePerformer() {
        super();
        setPageNum(1);
        setElemOnPage(8);
        setCommandText("Исполнитель");

    }

    public ChoosePerformer(String performerName) {
        this();
        setCommandName(performerName);

    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        commands = new ArrayList<>();
        commands.add(new ChooseDate("12.11.23"));
        commands.add(new ChooseDate("13.11.23"));
        commands.add(new ChooseDate("14.11.23"));
        commands.add(new ChooseDate("15.11.23"));
        commands.add(new ChooseDate("16.11.23"));
        commands.add(new ChooseDate("17.11.23"));
        commands.add(new ChooseDate("18.11.23"));
        commands.add(new ChooseDate("19.11.23"));
        commands.add(new ChooseDate("20.11.23"));
        commands.add(new ChooseDate("21.11.23"));
        commands.add(new ChooseDate("22.11.23"));
        commands.add(new ChooseDate("23.11.23"));
        commands.add(new ChooseDate("24.11.23"));
        commands.add(new ChooseDate("25.11.23"));
        commands.add(new ChooseDate("26.11.23"));
        commands.add(new ChooseDate("27.11.23"));
        commands.add(new ChooseDate("28.11.23"));
        commands.add(new ChooseDate("29.11.23"));
        commands.add(new ChooseDate("30.11.23"));
        commands.add(new ChooseDate("01.12.23"));
        commands.add(new ChooseDate("02.12.23"));
        commands.add(new ChooseDate("03.12.23"));
        commands.add(new ChooseDate("04.12.23"));
        commands.add(new ChooseDate("05.12.23"));
        commands.add(new ChooseDate("06.12.23"));

        List<AbstractCommandImpl> commandsToView = commands.subList(0, getElemOnPage());

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commandsToView, 2),true, true));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }

}
