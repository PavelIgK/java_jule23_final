package ru.sberbank.jd.botapp.model.commands;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sberbank.jd.botapp.model.ChatInfo;

import java.util.ArrayList;

@Data
@Slf4j
public abstract class AbstractCommandImpl implements Command {

    private String commandName;
    private String commandText;
    ArrayList<AbstractCommandImpl> commands;

    public AbstractCommandImpl() {
        commands = new ArrayList<>();
    }

    public ChatInfo execute(ChatInfo chatInfo) {
        return null;
    }

    public BotApiMethod execute(Update update) {
        return null;
    }

}
