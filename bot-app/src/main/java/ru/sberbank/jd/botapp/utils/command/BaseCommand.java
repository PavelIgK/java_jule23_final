package ru.sberbank.jd.botapp.utils.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.sberbank.jd.botapp.config.ConfigurationRepository;
import ru.sberbank.jd.botapp.repository.UserCacheRepository;

public interface BaseCommand {
    SendMessage getMessage(Long chatId, Long userId);
}
