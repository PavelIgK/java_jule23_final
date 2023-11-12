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
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность "Шаблон расписания сотрудника".
 *
 */
@Entity
@Getter
@Setter
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

}
