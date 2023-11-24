package ru.sberbank.jd.botapp.model.commands;

import ru.sberbank.jd.botapp.model.ChatInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда назад.
 *
 */
public class GoBack extends AbstractCommandImpl implements Command {
    public GoBack() {
        super();
        setCommandName("Назад");
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) { //костыль
        chatInfo.getMenuCache().remove(chatInfo.getMenuCache().size()-1); //удалим последнюю на которой стоим
        AbstractCommandImpl commandToOpen = chatInfo.getMenuCache().get(chatInfo.getMenuCache().size()-1);
        chatInfo.getMenuCache().remove(chatInfo.getMenuCache().size()-1); //удалим текущую, тк будет дубль

        return commandToOpen.execute(chatInfo);
    }
}
