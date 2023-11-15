package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sberbank.jd.dto.schedule.ScheduleTemplateDto;

/**
 * Сущность "Шаблон расписания сотрудника".
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Наименование расписания.
     */
    private String scheduleName;

    /**
     * Исполнитель услуг.
     */
    @ManyToOne
    private Performer performer;

    /**
     * Время начала периода оказания услуг.
     */
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    /**
     * Время окончания периода оказания услуг.
     */
    @Temporal(TemporalType.TIME)
    private LocalTime endTime;


    /**
     * Метод получения DTO из entity.
     *
     * @return DTO объект
     */
    public ScheduleTemplateDto toDto() {
        return ScheduleTemplateDto.builder()
                .id(this.getId())
                .startTime(this.getStartTime())
                .endTime(this.getEndTime())
                .performer(this.getPerformer().toDto())
                .build();
    }

    /**
     * Метод получения entity из DTO.
     *
     * @param scheduleTemplateDto DTO
     * @return entity
     */
    public static ScheduleTemplate of(ScheduleTemplateDto scheduleTemplateDto) {
        return ScheduleTemplate.builder()
                .id(scheduleTemplateDto.getId())
                .startTime(scheduleTemplateDto.getStartTime())
                .endTime(scheduleTemplateDto.getEndTime())
                .performer(Performer.of(scheduleTemplateDto.getPerformer()))
                .build();
    }


}
