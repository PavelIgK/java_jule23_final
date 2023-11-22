package ru.sberbank.jd.botapp.model.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.OrderInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import ru.sberbank.jd.botapp.service.PerformerService;
import ru.sberbank.jd.botapp.service.TimeSlotService;
import ru.sberbank.jd.dto.authorization.UserDto;

/**
 * Команда выбора времени.
 */
@Slf4j
public class ChoiseTime extends AbstractCommandImpl implements Command {

    public ChoiseTime() {
        super();
        setPageNum(1);
        setElemOnPage(8);
        setCommandText("Выберите доступное время");
    }

    public ChoiseTime(String date) {
        this();
        setCommandName(date);
    }

    public ChoiseTime(String date, String dateData) {
        this();
        setCommandName(date);
        setDataToSend(dateData);
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        String dateData = chatInfo.getCallbackData().getData();
        OrderInfo orderInfo = chatInfo.getOrderInfo();
        if (!dateData.isEmpty()) {
            orderInfo.setDate(dateData);
        }

        ApplicationContext ctx = AppContextManager.getAppContext();
        TimeSlotService timeSlotService = ctx.getBean(TimeSlotService.class);
        DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat dfToSend = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        commands = new ArrayList<>();

        try {
            timeSlotService.getTimeSlots(orderInfo.getPerformerId(), orderInfo.getDate(), orderInfo.getServiceId())
                    .forEach(it -> commands.add(new ConfirmOrder(df.format(it.getStartDateTime()),
                            dfToSend.format(it.getStartDateTime()))));
        } catch (HttpClientErrorException ex) {
            log.info("Нет доступных слотов. Для услуги {}, исполнитель {}, дата {} Ошибка:" + ex.getMessage(),
                    orderInfo.getPerformerId(),
                    orderInfo.getDate(),
                    orderInfo.getServiceId());
            setCommandText("К сожалению свободного времени у данного специалиста на этот день нет.");

        }

        if (commands.isEmpty()) {
            setCommandText("К сожалению свободного времени у данного специалиста на этот день нет.");
        }

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        if (commands.size() > getElemOnPage()) {
            List<AbstractCommandImpl> commandsToView = commands.subList(0, getElemOnPage());
            chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commandsToView, 2), true, true));
        } else {
            chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2), true, false));
        }

        chatInfo.getMenuCache().add(this);
        return chatInfo;

    }
}
