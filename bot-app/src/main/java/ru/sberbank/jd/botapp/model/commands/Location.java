package ru.sberbank.jd.botapp.model.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.model.ChatInfo;

/**
 * Команда информация.
 */
public class Location extends AbstractCommandImpl implements Command {

    private Double orgLong;
    private Double orgLat;

    public Location() {
        super();
        setCommandName("Показать на карте");
        this.orgLat = 54.395646491400136;
        this.orgLong = 39.2660171994134;
    }

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        chatInfo.setCallbackMsg(SendLocation.builder()
                .chatId(chatInfo.getChatId())
                .longitude(orgLong)
                .latitude(orgLat)
                .horizontalAccuracy(500.0)
                .build()
        );

        return chatInfo;
    }
}
