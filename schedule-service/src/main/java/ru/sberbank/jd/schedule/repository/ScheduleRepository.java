package ru.sberbank.jd.schedule.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sberbank.jd.schedule.entity.Schedule;

/**
 * Репозиторий для сущности "Расписание".
 *
 */
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    List<Schedule> getSchedulesByPerformer_Id(UUID id);


    @Query("select s from Schedule s where s.performer.id = :performer_id "
            + "and s.startDateTime >= :begin and s.endDateTime <= :end order by s.startDateTime")
    List<Schedule> getSchedulesByPerformer_IdAndPeriod(
            @Param("performer_id") UUID performerId,
            @Param("begin") Date begin,
            @Param("end") Date end);
}
