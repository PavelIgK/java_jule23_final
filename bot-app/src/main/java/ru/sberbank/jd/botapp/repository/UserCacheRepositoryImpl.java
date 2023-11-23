package ru.sberbank.jd.botapp.repository;


import ru.sberbank.jd.botapp.model.UserCache;
import java.util.*;


/**
 * Реализация хранилища.
 */
public class UserCacheRepositoryImpl implements UserCacheRepository {

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
}
