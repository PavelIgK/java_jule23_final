package ru.sberbank.jd.botapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.botapp.utils.command.impl.*;

import java.time.LocalDateTime;
import java.util.HashMap;

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


    /**
     * Отвечает за обработку сообщения либо на обновление.
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            Long userId = update.getCallbackQuery().getFrom().getId();
            callbackHandler(update);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long userId = update.getMessage().getFrom().getId();
            log.debug("MESSAGE = " + update.getMessage().toString());
            log.debug("CHAT = " + update.getMessage().getChat().toString());
            messageHandler(update);
        }
    }


    /**
     * Метод обработки сообщения от бота.
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
     * Отправка сообщения боту с геолокацией.
     *
     * @param sendMessage SendMessage
     * @param sendLocation SendLocation
     */
    private void sendMessage(SendMessage sendMessage, SendLocation sendLocation) {
        try {
            execute(sendMessage);
            execute(sendLocation);
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


    /**
     * Метод для обработки команды на уже имеющемся сообщении
     *
     * @param update входящий апдейт.
     */
    private void callbackHandler(Update update) {
        String messageOld = update.getCallbackQuery().getMessage().getText();

        Long chatId = update.getCallbackQuery().getMessage().getChat().getId();
        Integer messageID = update.getCallbackQuery().getMessage().getMessageId();
        Long userId = update.getCallbackQuery().getFrom().getId();

        String buttonCommand = update.getCallbackQuery().getData();
        String userFirstName = update.getCallbackQuery().getFrom().getFirstName();
        String userLastName = update.getCallbackQuery().getFrom().getLastName();
        String userName = update.getCallbackQuery().getFrom().getUserName();

        SendMessage sendMessage = new SendMessage();
        boolean backToMenu = false;
        switch (buttonCommand) {
            case "/menu":
            case "Меню":
                sendMessage = new Menu().getMessage(chatId, userFirstName);
                sendMessage(sendMessage);
                backToMenu = true;
                break;
            case "Записаться":
                sendMessage = new ChoiseSex().getMessage(chatId, userFirstName);
                break;
            case "Мужчина":
                sendMessage = new ManMenu().getMessage(chatId, userFirstName);
                break;
            case "Мои записи":
                sendMessage = new CurrentOrders().getMessage(chatId, userFirstName);
                break;
            case "Информация":
                sendMessage = new Information().getMessage(chatId, userFirstName);
                SendLocation location = SendLocation.builder()
                        .chatId(sendMessage.getChatId())
                        .longitude(botConfig.getOrgLong())
                        .latitude(botConfig.getOrgLat())
                        .horizontalAccuracy(500.0)
                        .build();
                sendMessage(sendMessage, location);
                backToMenu = true;
                break;
            default:
                sendMessage = new Menu().getMessage(chatId, userFirstName);
                sendMessage(sendMessage);
                backToMenu = true;

        }


        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageID)
                .text(sendMessage.getText())
                .replyMarkup((InlineKeyboardMarkup) sendMessage.getReplyMarkup())
                .build();

        if (backToMenu) {
            editMessageText.setText("Возвращаемся в меню");
            editMessageText.setReplyMarkup(null);
        }

        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
