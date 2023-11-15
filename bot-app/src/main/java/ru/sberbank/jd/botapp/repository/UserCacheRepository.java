package ru.sberbank.jd.botapp.repository;

import ru.sberbank.jd.botapp.model.UserCache;
import java.util.List;

/**
 * Интерфейс хранилища.
 */
public interface UserCacheRepository {

    void save(UserCache userCache);

    UserCache getById(Long id);

    UserCache removeById(Long id);

    List<UserCache> findAll();
    boolean contains(Long id);
}
