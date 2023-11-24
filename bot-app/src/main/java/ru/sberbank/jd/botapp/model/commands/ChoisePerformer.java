package ru.sberbank.jd.botapp.model.commands;

import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import ru.sberbank.jd.botapp.service.PerformerService;
import ru.sberbank.jd.botapp.utils.CommandCatalog;

/**
 * Команда выбора мастера.
 *
 */
public class ChoisePerformer extends AbstractCommandImpl implements Command {

    public ChoisePerformer() {
        super();
        setPageNum(1);
        setElemOnPage(8);
        setCommandText("Выберите мастера");
    }

    public ChoisePerformer(String service) {
        this();
        setCommandName(service);
    }

    public ChoisePerformer(String service, String serviceData) {
        this();
        setCommandName(service);
        setDataToSend(serviceData);
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        String serviceId = chatInfo.getCallbackData().getData();
        if (!serviceId.isEmpty()) {
            chatInfo.getOrderInfo().setServiceId(serviceId);
        }

        ApplicationContext ctx = AppContextManager.getAppContext();
        PerformerService performerService = ctx.getBean(PerformerService.class);

        commands = new ArrayList<>();

        performerService.findAllPerformers()
                .forEach(it -> commands.add(new ChoiseDate(it.getFirstName()
                        + " " + it.getLastName(), it.getId().toString())));

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2), true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }

}
