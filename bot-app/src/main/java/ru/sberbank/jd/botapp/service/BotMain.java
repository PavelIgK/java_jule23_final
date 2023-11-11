package ru.sberbank.jd.botapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.botapp.utils.command.impl.ChoiseSex;
import ru.sberbank.jd.botapp.utils.command.impl.ManMenu;
import ru.sberbank.jd.botapp.utils.command.impl.Menu;

@Slf4j
@Component
public class BotMain extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    public BotMain(@Value("${bot.token}") String botToken,
                       BotConfig botConfig) {
        super(botToken);
        this.botConfig = botConfig;

    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage()) {
            Long userId = update.getMessage().getFrom().getId();
            log.debug("MESSAGE = " + update.getMessage().toString());
            log.debug("CHAT = " + update.getMessage().getChat().toString());
            messageHandler(update);
        }
    }


    /**
     * Метод обработки сообщения боту.
     *
     * @param update входящий апдейт.
     */
    private void messageHandler(Update update) {
        String messageText = update.getMessage().getText();
        String command = messageText.contains(" ") ? messageText.substring(0, messageText.indexOf(" ")) : messageText;
        command = command.contains("@") ? command.substring(0, command.indexOf("@")) : command;

        String commandText = messageText.contains(" ") ? messageText.substring(messageText.indexOf(" ") + 1) : "";

        long chatId = update.getMessage().getChatId();
        String userFirstName = update.getMessage().getFrom().getFirstName();
        Long userId = update.getMessage().getFrom().getId();
        SendMessage sendMessage = new SendMessage();
        switch (command) {
            case "/start":
                startCommandReceived(chatId, userFirstName);
                break;
            case "/menu":
            case "Меню":
                sendMessage = new Menu().getMessage(chatId, userFirstName);
                sendMessage(sendMessage);
                break;
            case "Записаться":
                sendMessage = new ChoiseSex().getMessage(chatId, userFirstName);
                sendMessage(sendMessage);
                break;
            case "Мужчина":
                sendMessage = new ManMenu().getMessage(chatId, userFirstName);
                sendMessage(sendMessage);
                break;
        }
    }

    /**
     * Отправка сообщения боту.
     *
     * @param chatId     айди чата
     * @param textToSend текст для отправки
     */
    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Отправка сообщения боту.
     *
     * @param sendMessage SendMessage
     */
    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


    /**
     * Приветственное сообщение.
     *
     * @param chatId айди чата
     * @param name   имя пользователя
     */
    private void startCommandReceived(Long chatId, String name) {
        StringBuilder result = new StringBuilder();
        result.append("Привет, ")
                .append(name);
        sendMessage(chatId, result.toString());
    }



}
