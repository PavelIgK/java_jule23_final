package ru.sberbank.jd.botapp.model.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.spel.CodeFlow.ClinitAdder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.OrderInfo;
import ru.sberbank.jd.botapp.model.UserCache;
import ru.sberbank.jd.botapp.model.menu.Menu;
import ru.sberbank.jd.botapp.service.ClientService;
import ru.sberbank.jd.botapp.service.PerformerService;
import ru.sberbank.jd.botapp.service.ProvidedServiceService;
import ru.sberbank.jd.botapp.service.UserService;
import ru.sberbank.jd.dto.authorization.UserDto;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

/**
 * Команда выбора даты.
 */

public class ConfirmOrder extends AbstractCommandImpl implements Command {

    private String id;
    public ConfirmOrder() {
        super();
        setCommandText("Ваш заказ");

    }

    public ConfirmOrder(String choiseTime) {
        this();
        setCommandName(choiseTime);
    }

    public ConfirmOrder(String choiseTime, String choiseTimeData) {
        this();
        setCommandName(choiseTime);
        setDataToSend(choiseTimeData);
    }


    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        String timeData = chatInfo.getCallbackData().getData();
        OrderInfo orderInfo = chatInfo.getOrderInfo();

        ApplicationContext ctx = AppContextManager.getAppContext();
        //Получим id клиента по телеграм id
        UserService userService =  ctx.getBean(UserService.class);
        UserDto user = userService
                .getUserByTelegramId(chatInfo.getUserId().toString());

        //Сохраним клиента
        ClientService clientService =  ctx.getBean(ClientService.class);
        ClientDto client = clientService
                .getClientByUserId(user.getId().toString());
        orderInfo.setClient(client);

        //Сохраним заказчика
        PerformerService performerService =  ctx.getBean(PerformerService.class);
        PerformerDto performer = performerService
                .getPerformerById(chatInfo.getOrderInfo().getPerformerId());
        orderInfo.setPerformer(performer);

        //Сохраним Заказ
        ProvidedServiceService providedServiceService =  ctx.getBean(ProvidedServiceService.class);
        ProvidedServiceDto providedService = providedServiceService
                .getServicesById(chatInfo.getOrderInfo().getServiceId());
        orderInfo.setService(providedService);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(timeData, formatter);
        orderInfo.setStartDateTime(startDateTime);

        setCommandText("Давайте проверим запись:"
                + "\nДата: " + startDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                + "\nВремя:  " + startDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                + "\nМастер: " + performer.getFirstName()
                + " " + performer.getLastName()
                + "\nУслуга: " + providedService.getName()
                + "\nСтоимость: " + providedService.getPrice() + "₽"
                + "\n\nЕсли все верно нажмите подтвердить.");

        commands = new ArrayList<>();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();
        commands.add(new Confirm("Подтвердить", "Confirm"));
        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }

}
