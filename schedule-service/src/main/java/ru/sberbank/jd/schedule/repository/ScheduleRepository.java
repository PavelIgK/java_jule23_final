package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.Schedule;

/**
 * Репозиторий для сущности "Расписание".
 *
 */
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
