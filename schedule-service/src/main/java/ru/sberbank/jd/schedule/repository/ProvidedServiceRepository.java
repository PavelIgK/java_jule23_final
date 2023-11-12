package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.ProvidedService;

/**
 * Репозиторий для сущности "Услуга".
 *
 */
public interface ProvidedServiceRepository extends JpaRepository<ProvidedService, UUID> {
}
