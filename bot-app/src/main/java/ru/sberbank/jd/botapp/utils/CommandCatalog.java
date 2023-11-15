package ru.sberbank.jd.botapp.utils;

import lombok.Getter;

@Getter
public enum CommandCatalog {
    AUTHORIZATION("Авторизация"),
    LOGIN("Логин"),
    PASSWORD("Пароль"),
    MENU("Меню"),
    CHOISESEX("Записаться"),
    CURRENTORDERS("Мои записи"),
    INFORMATION("Информация"),
    MAN("Мужчина"),
    WOMAN("Женщина");

    private final String value;

    CommandCatalog(String value) {
        this.value = value;
    }

}
