package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.Performer;

/**
 * Репозиторий для сущности "Исполнитель услуги".
 *
 */
public interface PerformerRepository extends JpaRepository<Performer, UUID> {
}
