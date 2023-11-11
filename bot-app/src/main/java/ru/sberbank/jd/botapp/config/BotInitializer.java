package ru.sberbank.jd.botapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.sberbank.jd.botapp.service.BotMain;

@Component
@Slf4j
public class BotInitializer {
    private final BotMain botMain;

    @Autowired
    public BotInitializer(BotMain botMain) {
        this.botMain = botMain;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(botMain);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
