package ru.sberbank.jd.botapp.model.commands;

import lombok.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sberbank.jd.botapp.model.ChatInfo;

/**
 * Команда "неизвестная команда".
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Unknown extends AbstractCommandImpl implements Command{

    @Override
    public ChatInfo execute(ChatInfo chatInfo) {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatInfo.getChatId())
                .text("Команда не найдена. Попробуйте /start или /menu")
                .build();
        chatInfo.setCallbackMsg(sendMessage);

        return chatInfo;
    }
}
