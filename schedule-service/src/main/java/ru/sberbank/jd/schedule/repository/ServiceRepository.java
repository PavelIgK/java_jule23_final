package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.Service;

/**
 * Репозиторий для сущности "Услуга".
 *
 */
public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
