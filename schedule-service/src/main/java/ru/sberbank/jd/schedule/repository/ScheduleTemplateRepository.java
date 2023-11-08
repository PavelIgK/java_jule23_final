package ru.sberbank.jd.schedule.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.ScheduleTemplate;

/**
 * Репозиторий для сущности "Шаблон расписания".
 *
 */
public interface ScheduleTemplateRepository extends JpaRepository<ScheduleTemplate, UUID> {
}
