package ru.sberbank.jd.authorization.service;

import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.dto.authorization.UserDto;


/**
 * Сервис по работе с сущностью пользователя.
 *
 */
public interface UserService {


    /**
     * Получить всех пользователей.
     *
     * @return - список пользователей
     */
    List<UserDto> findAll();

    /**
     * Получить пользователя по UUID.
     *
     * @param id - UUID
     * @return - экземпляр пользователя
     */
    UserDto getUserById(UUID id);

    /**
     * Получить пользователя по telegramID.
     *
     * @param telegramId - телеграм id
     * @return - экземпляр пользователя
     */
    UserDto getUserByTelegramId(String telegramId);


    /**
     * Получить пользователя по логин паролю.
     *
     * @param login login
     * @param password password
     * @return - экземпляр пользователя
     */
    UserDto getUserByLoginAndPassword(String login, String password);

    /**
     * Получить пользователя по логин паролю.
     *
     * @param userDto userDto
     * @return - экземпляр пользователя
     */
    UserDto add(UserDto userDto);
}
