package ru.sberbank.jd.botapp.model.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.OrderInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import ru.sberbank.jd.botapp.service.OrderService;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.OrderDto;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

/**
 * Команда для кнопки "Подтвердить".
 *
 */

public class Confirm extends AbstractCommandImpl implements Command {

    private String id;
    public Confirm() {
        super();
        setCommandText("Ваш заказ");

    }

    public Confirm(String choiseTime) {
        this();
        setCommandName(choiseTime);
    }

    public Confirm(String choiseTime, String choiseTimeData) {
        this();
        setCommandName(choiseTime);
        setDataToSend(choiseTimeData);
    }


    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        //Таищм нужные данные
        OrderInfo orderInfo = chatInfo.getOrderInfo();
        PerformerDto performer = orderInfo.getPerformer();
        ClientDto client = orderInfo.getClient();
        ProvidedServiceDto service = orderInfo.getService();
        LocalDateTime startDate = orderInfo.getStartDateTime();
        LocalDateTime endDate = startDate.plusMinutes(orderInfo.getService().getDuration());

        //конвертим в Date чтоб пихнуть в апи
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart =  df.parse(startDate.toString());
            dateEnd =  df.parse(endDate.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        OrderDto orderDto = OrderDto.builder()
                .client(client)
                .performer(performer)
                .service(service)
                .startDateTime(dateStart)
                .endDateTime(dateEnd)
                .build();

        //Инициируем отправку
        ApplicationContext ctx = AppContextManager.getAppContext();
        OrderService orderService = ctx.getBean(OrderService.class);
        orderService.addOrder(orderDto);
        commands = new ArrayList<>();

        //Вешаем спасибо за заказ
        setCommandText("Заказ записан:"
                + "\nДата: " + startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                + "\nВремя:  " + startDate.format(DateTimeFormatter.ofPattern("HH:mm"))
                + "\nМастер: " + performer.getFirstName()
                + " " + performer.getLastName()
                + "\nУслуга: " + service.getName()
                + "\nСтоимость: " + service.getPrice() + "₽"
                + "\n\n Будем Вас ждать!");
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),false, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }

}
