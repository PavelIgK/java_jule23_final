package ru.sberbank.jd.botapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.botapp.config.ConfigurationRepository;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.UserCache;
import ru.sberbank.jd.botapp.model.commands.AbstractCommandImpl;
import ru.sberbank.jd.botapp.model.commands.MainMenu;
import ru.sberbank.jd.botapp.model.commands.Unknown;
import ru.sberbank.jd.botapp.repository.UserCacheRepository;

@Slf4j
@Component
public class BotMain extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    UserCacheRepository userCacheRepository = new ConfigurationRepository().userCacheRepository();
    private RestTemplate restTemplate = new RestTemplate();


    public BotMain(BotConfig botConfig) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    /**
     * Точка входа для всех сообщений к боту.
     *
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            callbackHandler(update);
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageHandler(update);
        }
    }

    private void messageHandler(Update update) {
        UserCache userCache = getUserCache(update);

        String command = update.getMessage().getText();
        switch (command) {
            case "/start":
            case "/menu":
                ChatInfo chatInfo = new MainMenu().execute(userCache.getChatInfo());
                sendMessageToUser(chatInfo.getCallbackMsg());
                userCache.setChatInfo(chatInfo);
                userCacheRepository.save(userCache);
                break;
            default:
                chatInfo = new Unknown().execute(userCache.getChatInfo());
                sendMessageToUser(chatInfo.getCallbackMsg());
        }
    }

    private void callbackHandler(Update update) {
        UserCache userCache = getUserCache(update);

        String button = update.getCallbackQuery().getData();
        try {
            AbstractCommandImpl command = (AbstractCommandImpl) Class.forName(button).newInstance();
            ChatInfo chatInfo = command.execute(userCache.getChatInfo());
            sendMessageToUser(chatInfo.getCallbackMsg());
            userCache.setChatInfo(chatInfo);
            userCacheRepository.save(userCache);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            sendMessageToUser(new Unknown().execute(update));
        }
    }

    /**
     * Отправка сообщения боту.
     *
     * @param botApiMethod SendMessage
     */
    private void sendMessageToUser(BotApiMethod botApiMethod) {
        try {
            execute(botApiMethod);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private ChatInfo getChatInfo(Update update) {
        String chatId = null;
        Long userId = null;
        Integer messageId = null;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChat().getId().toString();
            userId = update.getCallbackQuery().getFrom().getId();
            messageId = update.getCallbackQuery().getMessage().getMessageId();
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            userId = update.getMessage().getFrom().getId();
            chatId = update.getMessage().getChatId().toString();
        }
        return ChatInfo.builder()
                .chatId(chatId)
                .messageId(messageId)
                .userId(userId)
                .build();
    }

    private UserCache getUserCache(Update update) {
        ChatInfo chatInfo = getChatInfo(update);

        if (userCacheRepository.contains(chatInfo.getUserId())) {
            UserCache userCache = userCacheRepository.getById(chatInfo.getUserId());
            userCache.getChatInfo().setChatId(chatInfo.getChatId());
            userCache.getChatInfo().setUserId(chatInfo.getUserId());
            userCache.getChatInfo().setMessageId(chatInfo.getMessageId());
            return userCache;
        } else {
            return UserCache.builder()
                    .userIdTelegram(chatInfo.getUserId())
                    .chatInfo(chatInfo)
                    .build();
        }
    }


}
