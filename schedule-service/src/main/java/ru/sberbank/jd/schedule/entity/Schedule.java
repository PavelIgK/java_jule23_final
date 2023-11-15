package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sberbank.jd.dto.schedule.ScheduleDto;

/**
 * Сущность "Расписание сотрудника".
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Исполнитель услуг.
     */
    @ManyToOne
    private Performer performer;

    /**
     * Дата и время начала периода оказания услуг.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    /**
     * Дата и время окончания периода оказания услуг.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    /**
     * Метод получения DTO из entity.
     *
     * @return DTO объект
     */
    public ScheduleDto toDto() {
        return ScheduleDto.builder()
                .id(this.getId())
                .startDateTime(this.getStartDateTime())
                .endDateTime(this.getEndDateTime())
                .performer(this.getPerformer().toDto())
                .build();
    }

    /**
     * Метод получения entity из DTO.
     *
     * @param scheduleDto DTO
     * @return entity
     */
    public static Schedule of(ScheduleDto scheduleDto) {
        return Schedule.builder()
                .id(scheduleDto.getId())
                .startDateTime(scheduleDto.getStartDateTime())
                .endDateTime(scheduleDto.getEndDateTime())
                .performer(Performer.of(scheduleDto.getPerformer()))
                .build();
    }

}
