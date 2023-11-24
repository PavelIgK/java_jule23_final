package ru.sberbank.jd.botapp.repository;

import ru.sberbank.jd.botapp.model.UserCache;
import java.util.List;

/**
 * Интерфейс хранилища для кэша пользователей.
 *
 */
public interface UserCacheRepository {

    void save(UserCache userCache);

    UserCache getByUserIdTelegram(Long id);

    UserCache removeByUserIdTelegram(Long id);

    List<UserCache> findAll();
    boolean contains(Long id);
}
