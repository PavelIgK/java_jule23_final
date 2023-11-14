package ru.sberbank.jd.authorization.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.authorization.entity.User;
import ru.sberbank.jd.authorization.exception.UserNotFoundException;
import ru.sberbank.jd.authorization.exception.UserPasswordIncorrect;
import ru.sberbank.jd.authorization.repository.UserRepository;
import ru.sberbank.jd.dto.authorization.UserDto;


/**
 * Реализация сервиса по работе с сущностью пользователя.
 *
 */
@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Получить пользователя по UUID.
     *
     * @param id - UUID
     * @return - экземпляр пользователя
     */
    @Override
    public UserDto getUserById(UUID id) {
        User user = userRepository.getUsersById(id)
                .orElseThrow(() -> new UserNotFoundException("Нет пользователя с id = " + id));
        return user.toDto();
    }

    /**
     * Получить пользователя по логин паролю.
     *
     * @param login login
     * @param password password
     * @return - экземпляр пользователя
     */
    @Override
    public UserDto getUserByLoginAndPassword(String login, String password) {
        userRepository.getUsersByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Нет пользователя с логином = " + login));

        User user = userRepository.getUserByLoginAndPassword(login, password)
                .orElseThrow(() -> new UserPasswordIncorrect("Пароль пользователя некорректен = " + login));

        return user.toDto();
    }
}
