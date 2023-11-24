package ru.sberbank.jd.botapp.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.sberbank.jd.botapp.model.commands.AbstractCommandImpl;

import java.util.ArrayList;
import ru.sberbank.jd.dto.authorization.UserDto;

/**
 * Информация о текущем чате.
 *
 */
@Data
@Builder
public class ChatInfo {

    private String chatId;
    private Long userId;
    private Integer messageId;
    private OrderInfo orderInfo;
    private ArrayList<AbstractCommandImpl> menuCache;
    private BotApiMethod callbackMsg;
    private CallbackData callbackData;
    private UserDto client;

}
