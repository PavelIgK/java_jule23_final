package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность "Расписание сотрудника".
 *
 */
@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Id исполнителя услуг.
     */
    private UUID userId;

    /**
     * Логин исполнителя услуг.
     */
    private String login;

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

}
