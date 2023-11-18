//package ru.sberbank.jd.botapp.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import ru.sberbank.jd.botapp.config.BotConfig;
//import ru.sberbank.jd.botapp.config.ConfigurationRepository;
//import ru.sberbank.jd.botapp.model.UserCache;
//import ru.sberbank.jd.botapp.model.commands.*;
//import ru.sberbank.jd.botapp.repository.UserCacheRepository;
//import ru.sberbank.jd.botapp.utils.commands.*;
//import ru.sberbank.jd.dto.authorization.UserDto;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class Dump extends TelegramLongPollingBot {
//
//    private final BotConfig botConfig;
//
//    UserCacheRepository userCacheRepository = new ConfigurationRepository().userCacheRepository();
//    private RestTemplate restTemplate = new RestTemplate();
//
//    public BotMain(@Value("${bot.token}") String botToken, BotConfig botConfig) {
//        super(botToken);
//        this.botConfig = botConfig;
//    }
//
//    @Override
//    public String getBotUsername() {
//        return botConfig.getBotName();
//    }
//
//    /**
//     * Отвечает за обработку сообщения либо на обновление.
//     *
//     * @param update Update received
//     */
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasCallbackQuery()) {
//            Long userId = update.getCallbackQuery().getFrom().getId();
//            checkAuthorization(userId);
//            callbackHandler(update);
//        }
//
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            Long userId = update.getMessage().getFrom().getId();
//            checkAuthorization(userId);
//            log.debug("MESSAGE = " + update.getMessage().toString());
//            log.debug("CHAT = " + update.getMessage().getChat().toString());
//            messageHandler(update);
//        }
//    }
//
//    /**
//     * Метод обработки сообщения от бота.
//     *
//     * @param update входящий апдейт.
//     */
//    private void messageHandler(Update update) {
//        String messageText = update.getMessage().getText();
//        String command = messageText.contains(" ") ? messageText.substring(0, messageText.indexOf(" ")) : messageText;
//
//        long chatId = update.getMessage().getChatId();
//        String userFirstName = update.getMessage().getFrom().getFirstName();
//        String userLastName = update.getMessage().getFrom().getLastName();
//        Long userId = update.getMessage().getFrom().getId();
//        switch (command) {
//            case "/start":
//            case "/menu":
//                UserCache currentuserCache = userCacheRepository.getById(userId);
//                currentuserCache.getBreadcrumbs().clear();
//                currentuserCache.getBreadcrumbs().add(CommandCatalog.MENU);
//                currentuserCache.setFirstName(userFirstName);
//                currentuserCache.setLastName(userLastName);
//                currentuserCache.setCreateDateTime(LocalDateTime.now());
//                SendMessage sendMessage = new Menu().getMessage(chatId, userId);
//                sendMessage(sendMessage);
//                break;
//            default:
//                sendMessage(chatId, "Команда не найдена. Попробуйте /start или /menu");
//        }
//    }
//
//    /**
//     * Отправка сообщения боту.
//     *
//     * @param chatId     айди чата
//     * @param textToSend текст для отправки
//     */
//    private void sendMessage(Long chatId, String textToSend) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(String.valueOf(chatId));
//        sendMessage.setText(textToSend);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    /**
//     * Отправка сообщения боту.
//     *
//     * @param sendMessage SendMessage
//     */
//    private void sendMessage(SendMessage sendMessage) {
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//
//    /**
//     * Отправка сообщения боту с геолокацией.
//     *
//     * @param sendMessage  SendMessage
//     * @param sendLocation SendLocation
//     */
//    private void sendMessage(SendMessage sendMessage, SendLocation sendLocation) {
//        try {
//            execute(sendMessage);
//            execute(sendLocation);
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//
//    /**
//     * Метод для обработки команды на уже имеющемся сообщении
//     *
//     * @param update входящий апдейт.
//     */
//    private void callbackHandler(Update update) {
//        String messageOld = update.getCallbackQuery().getMessage().getText();
//
//        Long chatId = update.getCallbackQuery().getMessage().getChat().getId();
//        Integer messageID = update.getCallbackQuery().getMessage().getMessageId();
//        Long userId = update.getCallbackQuery().getFrom().getId();
//
//        String buttonCommand = update.getCallbackQuery().getData();
//        String userFirstName = update.getCallbackQuery().getFrom().getFirstName();
//        String userLastName = update.getCallbackQuery().getFrom().getLastName();
//        String userName = update.getCallbackQuery().getFrom().getUserName();
//
//        SendMessage sendMessage = new SendMessage();
//
//        UserCache currentuserCache = userCacheRepository.getById(userId);
//
//        boolean backToMenu = false;
//        switch (buttonCommand) {
//            case "/menu":
//            case "Меню":
//                sendMessage = new Menu().getMessage(chatId, userId);
//                sendMessage(sendMessage);
//                backToMenu = true;
//                break;
//            case "Записаться":
//
//            case "Мужчина":
//                sendMessage = new MainMenu().getMessage(chatId, userId);
//                currentuserCache.getBreadcrumbs().add(CommandCatalog.MAN);
//                break;
//            case "Мои записи":
//                sendMessage = new CurrentOrders().getMessage(chatId, userId);
//                currentuserCache.getBreadcrumbs().add(CommandCatalog.CURRENTORDERS);
//                break;
//            case "Информация":
//                sendMessage = new Information().getMessage(chatId, userId);
//                currentuserCache.getBreadcrumbs().add(CommandCatalog.INFORMATION);
//                SendLocation location = SendLocation.builder()
//                        .chatId(sendMessage.getChatId())
//                        .longitude(botConfig.getOrgLong())
//                        .latitude(botConfig.getOrgLat())
//                        .horizontalAccuracy(500.0)
//                        .build();
//                sendMessage(sendMessage, location);
//                backToMenu = true;
//                break;
//            default:
//                sendMessage = new Menu().getMessage(chatId, userId);
//                sendMessage(sendMessage);
//                backToMenu = true;
//        }
//
//
//        EditMessageText editMessageText = EditMessageText.builder()
//                .chatId(chatId)
//                .messageId(messageID)
//                .text(sendMessage.getText())
//                .replyMarkup((InlineKeyboardMarkup) sendMessage.getReplyMarkup())
//                .build();
//
//        if (backToMenu) {
//            editMessageText.setText("Возвращаемся в меню");
//            currentuserCache.getBreadcrumbs().clear();
//            currentuserCache.getBreadcrumbs().add(CommandCatalog.MENU);
//            editMessageText.setReplyMarkup(null);
//        }
//
//        try {
//            execute(editMessageText);
//        } catch (TelegramApiException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    private void checkAuthorization(Long userId) {
//        if (userCacheRepository.contains(userId)) {
//            if (userCacheRepository.getById(userId).isAuthorization()) {
//                return;
//            }
//        }
//
//        String url =  botConfig.getScheduleServiceUrl() + "/user/telegramId/{userId}";
//        Map<String, String> params = new HashMap<>();
//        params.put("userId", userId.toString());
//
//        UserDto userDto;
//
//        try {
//            userDto = restTemplate.getForObject(url, UserDto.class, params);
//        } catch (HttpClientErrorException ex) {
//            log.info("Не удалось получить данные. По telegramId:  " + userId + ". Ошибка:" + ex.getMessage());
//            userDto = createUser(userId);
//        }
//
//        UserCache userCache = UserCache.builder()
//                .userLogin(userDto.getLogin())
//                .userIdTelegram(Long.valueOf(userDto.getTelegramId()))
//                .createDateTime(LocalDateTime.now())
//                .authorization(true)
//                .build();
//        userCacheRepository.save(userCache);
//
//    }
//
//    public UserDto createUser(Long userId) {
//
//        String url = botConfig.getScheduleServiceUrl() + "/user";
//
//        UserDto userDtoCreated = UserDto.builder()
//                .login(userId.toString())
//                .telegramId(userId.toString())
//                .enabled(true)
//                .build();
//
//        return restTemplate.postForObject(url, userDtoCreated, UserDto.class);
//    }
//}
