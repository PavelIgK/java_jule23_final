package ru.sberbank.jd.authorization.service;

import java.util.UUID;
import ru.sberbank.jd.dto.authorization.UserDto;


/**
 * Сервис по работе с сущностью пользователя.
 *
 */
public interface UserService {

    /**
     * Получить пользователя по UUID.
     *
     * @param id - UUID
     * @return - экземпляр пользователя
     */
    UserDto getUserById(UUID id);

    /**
     * Получить пользователя по логин паролю.
     *
     * @param login login
     * @param password password
     * @return - экземпляр пользователя
     */
    UserDto getUserByLoginAndPassword(String login, String password);
}
