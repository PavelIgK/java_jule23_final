package ru.sberbank.jd.botapp.repository;


import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.botapp.model.UserCache;
import java.util.*;


/**
 * Реализация хранилища.
 */
@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class UserCacheRepositoryImpl implements UserCacheRepository {

    private final BotConfig botConfig;

    private final Map<Long, UserCache> storage = new HashMap<>();

    @Override
    public void save(UserCache userCache) {
        storage.put(userCache.getUserIdTelegram(), userCache);
    }

    @Override
    public UserCache getByUserIdTelegram(Long id) {
        return storage.get(id);
    }

    @Override
    public UserCache removeByUserIdTelegram(Long id) {
        return storage.remove(id);
    }

    @Override
    public List<UserCache> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean contains(Long id) {
        return storage.containsKey(id);
    }

    /**
     * Удаляем устаревшие записи.
     *
     * @throws InterruptedException
     */
    @Scheduled(cron = "${actualize-cache-cron}")
    public void actualize() throws InterruptedException {
        LocalDateTime currentDateTime = LocalDateTime.now();

        //Вытащим кого надо удалить
        List<Long> oldUserCache = storage.entrySet().stream()
                .filter(it -> it.getValue().getCreateDateTime().plusSeconds(botConfig.getCacheLifetime())
                        .isBefore(currentDateTime))
                .map(it -> it.getKey())
                .toList();
        //Удаляем из кеша
        Optional.ofNullable(oldUserCache).orElse(Collections.emptyList())
                .forEach(storage::remove);
    }
}
