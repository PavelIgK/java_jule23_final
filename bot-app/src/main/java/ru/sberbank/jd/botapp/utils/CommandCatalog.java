package ru.sberbank.jd.botapp.utils;

import java.util.Arrays;
import lombok.Getter;

/**
 * Список команд и их классов.
 */
@Getter
public enum CommandCatalog {
    //Menu
    MENU("ru.sberbank.jd.botapp.model.commands.MainMenu"),

    //User-Flow
    CHOISESERVICE("ru.sberbank.jd.botapp.model.commands.ChoiseService"),
    CHOISEPERFORMER("ru.sberbank.jd.botapp.model.commands.ChoisePerformer"),
    CHOISEDATE("ru.sberbank.jd.botapp.model.commands.ChoiseDate"),
    CHOISETIME("ru.sberbank.jd.botapp.model.commands.ChoiseTime"),
    CONFIRMORDER("ru.sberbank.jd.botapp.model.commands.ConfirmOrder"),
    CONFIRM("ru.sberbank.jd.botapp.model.commands.Confirm"),
    //Util command
    GOBACK("ru.sberbank.jd.botapp.model.commands.GoBack"),
    INFORMATION("ru.sberbank.jd.botapp.model.commands.Information"),
    LOCATION("ru.sberbank.jd.botapp.model.commands.Location"),
    MYORDERS("ru.sberbank.jd.botapp.model.commands.MyOrders"),
    SCROLLBACK("ru.sberbank.jd.botapp.model.commands.ScrollBack"),
    SCROLLFORWARD("ru.sberbank.jd.botapp.model.commands.ScrollForward"),
    UNKNOWN("ru.sberbank.jd.botapp.model.commands.Unknown");


    private final String className;

    CommandCatalog(String value) {
        this.className = value;
    }

    /**
     * ПОлучение enum команды по ее классу.
     *
     * @param className класс команды.
     * @return CommandCatalog
     */
    public static CommandCatalog valueOfClassName(String className) {
        return Arrays.stream(values())
                .filter(it -> it.className.equals(className))
                .findFirst()
                .orElse(null);
    }

}