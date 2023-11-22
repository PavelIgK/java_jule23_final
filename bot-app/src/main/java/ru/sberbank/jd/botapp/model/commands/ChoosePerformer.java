package ru.sberbank.jd.botapp.model.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 * Команда выбора даты.
 */

public class ChoosePerformer extends AbstractCommandImpl implements Command {

    private String id;
    public ChoosePerformer() {
        super();
        setPageNum(1);
        setElemOnPage(8);
        setCommandText("Выберите дату");

    }

    public ChoosePerformer(String performerName) {
        this();
        setCommandName(performerName);

    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        commands = new ArrayList<>();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd.MM.yy");
        c.setTime(dt);
        for (int i = 0; i < 14; i++) {
            dt = c.getTime();
            commands.add(new ChooseDate(df.format(dt)));
            c.add(Calendar.DATE, 1);
        }

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
