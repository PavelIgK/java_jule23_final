package ru.sberbank.jd.botapp.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.sberbank.jd.botapp.model.commands.AbstractCommandImpl;

import java.util.ArrayList;

@Data
@Builder
public class ChatInfo {

    private String chatId;
    private Long userId;
    private Integer messageId;
    private OrderInfo orderInfo;
    private ArrayList<AbstractCommandImpl> menuCache;
    private BotApiMethod callbackMsg;

}
