package ru.sberbank.jd.authorization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.authorization.entity.User;

/**
 * Репозиторий для работы с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAll();

    Optional<User> getByLogin(String login);

    Optional<User> getUsersById(UUID id);

    Optional<User> getUserByTelegramId(String telegramId);

    Optional<User> getUsersByLogin(String login);

    Optional<User> getUserByLoginAndPassword(String login, String password);
}
