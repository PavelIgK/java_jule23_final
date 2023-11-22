package ru.sberbank.jd.botapp.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.botapp.config.ConfigurationRepository;
import ru.sberbank.jd.botapp.model.CallbackData;
import ru.sberbank.jd.botapp.model.ChatInfo;
import ru.sberbank.jd.botapp.model.UserCache;
import ru.sberbank.jd.botapp.model.commands.AbstractCommandImpl;
import ru.sberbank.jd.botapp.model.commands.MainMenu;
import ru.sberbank.jd.botapp.model.commands.Unknown;
import ru.sberbank.jd.botapp.repository.UserCacheRepository;
import ru.sberbank.jd.botapp.utils.CommandCatalog;
import ru.sberbank.jd.dto.authorization.UserDto;

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

        String[] inputDatas = update.getCallbackQuery().getData().split(";");
        String className = CommandCatalog.valueOf(inputDatas[0]).getClassName();
        String data = "";
        if (inputDatas.length == 2) {
            data = inputDatas[1];
        }

        userCache.getChatInfo().setCallbackData(CallbackData.builder()
                .commandClass(className)
                .data(data)
                .build());

        try {
            AbstractCommandImpl command = (AbstractCommandImpl) Class.forName(className).newInstance();
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
        Long userId = chatInfo.getUserId();

        if (userCacheRepository.contains(userId)) {
            UserCache userCache = userCacheRepository.getByUserIdTelegram(chatInfo.getUserId());
            userCache.getChatInfo().setChatId(chatInfo.getChatId());
            userCache.getChatInfo().setUserId(chatInfo.getUserId());
            userCache.getChatInfo().setMessageId(chatInfo.getMessageId());
            return userCache;
        }

        String url = botConfig.getScheduleServiceUrl() + "/user/telegramId/{userId}";
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId.toString());
        UserDto userDto;

        try {
            userDto = restTemplate.getForObject(url, UserDto.class, params);
        } catch (HttpClientErrorException ex) {
            log.info("Не удалось получить данные. По telegramId:  " + userId + ". Ошибка:" + ex.getMessage());
            userDto = createUser(update);
        }

        assert userDto != null;
        return UserCache.builder()
                .userId(userDto.getId().toString())
                .userLogin(userDto.getLogin())
                .userIdTelegram(Long.valueOf(userDto.getTelegramId()))
                .createDateTime(LocalDateTime.now())
                .authorization(true)
                .chatInfo(chatInfo)
                .build();
    }

    public UserDto createUser(Update update) {
        Long userId = getChatInfo(update).getUserId();
        String url = botConfig.getScheduleServiceUrl() + "/user";

        UserDto userDtoCreated = UserDto.builder()
                .login(userId.toString())
                .telegramId(userId.toString())
                .enabled(true)
                .build();

        return restTemplate.postForObject(url, userDtoCreated, UserDto.class);
    }

}
