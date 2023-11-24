package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Команда "вперед" для паджинации.
 *
 */
public class ScrollForward extends AbstractCommandImpl implements Command {
    public ScrollForward() {
        super();
        setCommandName(">>");
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        AbstractCommandImpl lastCommand = chatInfo.getMenuCache().get(chatInfo.getMenuCache().size() - 1);
        ArrayList<AbstractCommandImpl> commandsList = lastCommand.getCommands();
        int commandElemOnPage = lastCommand.getElemOnPage();
        int commandPageNum = lastCommand.getPageNum();

        AtomicInteger atomicCt = new AtomicInteger();
        Map<Integer, List<AbstractCommandImpl>> mapOfChunks = commandsList.stream()
                .collect(Collectors.groupingBy(it -> atomicCt.getAndIncrement() / commandElemOnPage));

        ArrayList<List<AbstractCommandImpl>> commandsMap = new ArrayList<>(mapOfChunks.values());

        int abs = commandsList.size() % commandElemOnPage;
        int mod = commandsList.size() / commandElemOnPage;
        int pages = (abs == 0) ? mod : mod + 1;

        commandPageNum = (commandPageNum + 1) <= pages ? commandPageNum + 1 : 1;
        lastCommand.setPageNum(commandPageNum);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text(lastCommand.getCommandText())
                .build();

        chatInfo.setCallbackMsg(Menu.getKeyboard(chatInfo, sendMessage, Menu.createMenu(commandsMap.get(commandPageNum - 1), 2), true, true));

        return chatInfo;
    }
}
