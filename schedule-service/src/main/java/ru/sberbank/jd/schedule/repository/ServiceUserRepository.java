package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.ServiceUser;

/**
 * Репозиторий для связки сущностей "Услуга" и "Пользователь".
 *
 */
public interface ServiceUserRepository extends JpaRepository<ServiceUser, UUID> {
}
