package ru.sberbank.jd.botapp.model.commands;

import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.AppContextManager;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;
import ru.sberbank.jd.botapp.service.PerformerService;

/**
 * Команда выбора мастера.
 */
public class ChooseService extends AbstractCommandImpl implements Command {

    public ChooseService() {
        super();
        setPageNum(1);
        setElemOnPage(8);
        setCommandText("Выберите мастера");
    }

    public ChooseService(String service) {
        this();
        setCommandName(service);
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        ApplicationContext ctx = AppContextManager.getAppContext();
        PerformerService performerService =  ctx.getBean(PerformerService.class);

        commands = new ArrayList<>();

        performerService.findAllPerformers()
                .forEach(performerDto -> commands.add(new ChoosePerformer(performerDto.getFirstName()
                        + " "+ performerDto.getLastName())));

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(this.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commands, 2),true, false));

        chatInfo.getMenuCache().add(this);

        return chatInfo;
    }

}
