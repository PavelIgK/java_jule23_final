package ru.sberbank.jd.botapp.utils;

import lombok.Getter;

@Getter
public enum CommandCatalog {
    MENU("ru.sberbank.jd.botapp.model.commands.MainMenu"),
    CREATEORDER("ru.sberbank.jd.botapp.model.commands.CreateOrder"),
    CHOOSESERVICE("ru.sberbank.jd.botapp.model.commands.ChooseService"),
    CHOOSEPERFORMER("ru.sberbank.jd.botapp.model.commands.ChoosePerformer"),
    CHOOSEDATE("ru.sberbank.jd.botapp.model.commands.ChooseDate"),
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

    public static CommandCatalog valueOfClassName(String className) {
        for (CommandCatalog e : values()) {
            if (e.className.equals(className)) {
                return e;
            }
        }
        return null;
    }

}