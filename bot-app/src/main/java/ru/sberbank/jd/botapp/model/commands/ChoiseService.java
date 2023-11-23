package ru.sberbank.jd.botapp.model.commands;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.OrderInfo;
import ru.sberbank.jd.botapp.model.UserCache;
import ru.sberbank.jd.botapp.model.menu.Menu;
import java.util.ArrayList;
import ru.sberbank.jd.botapp.service.ProvidedServiceService;

/**
 * Команда записаться.
 */
public class ChoiseService extends AbstractCommandImpl implements Command {

    public ChoiseService(){
        super();
        setPageNum(1);
        setElemOnPage(4);
        setCommandName("Записаться");
        setCommandText("Выберите услугу");
    }
    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        chatInfo.setOrderInfo(OrderInfo.builder().build());
        setCommandText("Выберите услугу");
        ApplicationContext ctx = AppContextManager.getAppContext();
        ProvidedServiceService providedServiceService =  ctx.getBean(ProvidedServiceService.class);

        commands = new ArrayList<>();
        providedServiceService.findAllServices().forEach(it -> {
            commands.add(new ChoisePerformer(it.getName(), it.getId().toString()));
            setCommandText(getCommandText() + "\n" + it.getName() + " цена: " + it.getPrice() + "₽");
        });

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;

    }
}
