package ru.sberbank.jd.authorization.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.authorization.entity.User;

/**
 * Репозиторий для работы с пользователями.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User getByLogin(String login);
}
