package ru.sberbank.jd.botapp.model.commands;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import java.util.ArrayList;
import ru.sberbank.jd.botapp.service.ProvidedServiceService;

/**
 * Команда записаться.
 */
public class CreateOrder extends AbstractCommandImpl implements Command {

    public CreateOrder(){
        super();
        setPageNum(1);
        setElemOnPage(4);
        setCommandName("Записаться");
        setCommandText("Выберите услугу");
    }
    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        setCommandText("Выберите услугу");
        ApplicationContext ctx = AppContextManager.getAppContext();
        ProvidedServiceService providedServiceService =  ctx.getBean(ProvidedServiceService.class);

        commands = new ArrayList<>();
        providedServiceService.findAllServices().forEach(it -> {
            commands.add(new ChooseService(it.getName()));
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
