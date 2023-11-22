package ru.sberbank.jd.schedule.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.authorization.entity.User;
import ru.sberbank.jd.schedule.entity.Client;

/**
 * Репозиторий для сущности "Клиент".
 *
 */
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findClientByUserId(UUID id);
}
