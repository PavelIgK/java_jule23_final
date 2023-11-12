package ru.sberbank.jd.authorization.service;

import ru.sberbank.jd.authorization.entity.User;

/**
 * Сервис по работе с сущностью пользователя.
 *
 */
public interface UserService {

    /**
     * Получить пользователя по логину.
     *
     * @param login - логин
     * @return - экземпляр пользователя
     */
    User getUserByLogin(String login);
}
