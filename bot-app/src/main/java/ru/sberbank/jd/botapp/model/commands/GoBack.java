package ru.sberbank.jd.botapp.model.commands;

import ru.sberbank.jd.botapp.model.ChatInfo;

/**
 * Команда информация.
 */
public class GoBack extends AbstractCommandImpl implements Command {
    public GoBack() {
        super();
        setCommandName("Назад");
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {
        chatInfo.getMenuCache().remove(chatInfo.getMenuCache().size()-1);
        AbstractCommandImpl commandToOpen = chatInfo.getMenuCache().get(chatInfo.getMenuCache().size()-1);


        return commandToOpen.execute(chatInfo);
    }
}
