package ru.sberbank.jd.schedule.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.schedule.entity.ScheduleTemplate;

/**
 * Репозиторий для сущности "Шаблон расписания".
 *
 */
public interface ScheduleTemplateRepository extends JpaRepository<ScheduleTemplate, UUID> {

    List<ScheduleTemplate> getScheduleTemplatesByPerformer_Id(UUID id);

    ScheduleTemplate getScheduleTemplatesByPerformer_IdAndScheduleName(UUID id, String name);
}
