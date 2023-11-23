package ru.sberbank.jd.botapp.model.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import ru.sberbank.jd.botapp.service.ClientService;
import ru.sberbank.jd.botapp.service.OrderService;
import ru.sberbank.jd.botapp.service.UserService;
import ru.sberbank.jd.dto.authorization.UserDto;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.OrderDto;

/**
 * Команда текущие заказы.
 */
public class MyOrders extends AbstractCommandImpl implements Command {

    public MyOrders() {
        super();
        setCommandName("Мои записи");
        setCommandText("Ваши записи");
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        ApplicationContext ctx = AppContextManager.getAppContext();

        //У нас только телеграм айди, нужно добраться до клиента
        UserService userService = ctx.getBean(UserService.class);
        ClientService clientService = ctx.getBean(ClientService.class);
        OrderService orderService = ctx.getBean(OrderService.class);
        //Получаем юзера по телеге
        UserDto user = userService.getUserByTelegramId(chatInfo.getUserId().toString());
        //Получаем клиента по айди юзера
        ClientDto client = clientService.getClientByUserId(user.getId().toString());
        //Получаем список заказов
        List<OrderDto> orders = orderService.findOrdersByClient(client.getId().toString(), true);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");


        setCommandText("\n Записей нет");

        //Пройдемся по списку заказов если он не пуст
        AtomicReference<String> resultText = new AtomicReference<>("Список предстоящих услуг:");
        Optional.ofNullable(orders).orElse(Collections.emptyList()).forEach(it -> {
            resultText.set(resultText + "\n\nДата начала: " + df.format(it.getStartDateTime())
                    + "\nУслуга: " + it.getService().getName()
                    + "\nИсполнитель: " + it.getPerformer().getFirstName() + " " + it.getPerformer().getLastName()
                    + "\nСтоимость: " + it.getService().getPrice());
        });

        //Если заказы есть, обновим текст сообщения
        if (!orders.isEmpty()) {
            setCommandText(resultText.toString());
        }

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2), true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }
}
