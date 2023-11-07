package ru.sberbank.jd.authorization.service;

import lombok.RequiredArgsConstructor;
import ru.sberbank.jd.authorization.entity.User;
import ru.sberbank.jd.authorization.repository.UserRepository;

/**
 * Реализация сервиса по работе с сущностью пользователя.
 *
 */
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Получить пользователя по логину.
     *
     * @param login - логин
     * @return - экземпляр пользователя
     */
    @Override
    public User getUserByLogin(String login) {
        return userRepository.getByLogin(login);
    }
}
