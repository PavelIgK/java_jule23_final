package ru.sberbank.jd.botapp.model.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import java.util.ArrayList;
import java.util.List;
import ru.sberbank.jd.botapp.utils.CommandCatalog;

/**
 * Команда выбора даты.
 *
 */

public class ChoiseDate extends AbstractCommandImpl implements Command {

    private String id;
    public ChoiseDate() {
        super();
        setPageNum(1);
        setElemOnPage(8);
        setCommandText("Выберите дату");

    }

    public ChoiseDate(String performerName) {
        this();
        setCommandName(performerName);
    }

    public ChoiseDate(String performer, String performerData) {
        this();
        setCommandName(performer);
        setDataToSend(performerData);
    }


    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        String performerId = chatInfo.getCallbackData().getData();
        if (!performerId.isEmpty()) {
            chatInfo.getOrderInfo().setPerformerId(performerId);
        }
        commands = new ArrayList<>();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat dfSend = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(dt);
        for (int i = 0; i < 14; i++) {
            dt = c.getTime();
            commands.add(new ChoiseTime(df.format(dt), dfSend.format(dt)));
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
