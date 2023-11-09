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
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность "Заказ услуги".
 *
 */
@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Дата и время начала услуги.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    /**
     * Дата и время окончания услуги.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    /**
     * Id исполнителя услуг.
     */
    private String performerId;

    /**
     * Логин исполнителя услуг.
     */
    private String performerLogin;

    /**
     * Id клиента.
     */
    private String clientId;

    /**
     * Логин клиента.
     */
    private String clientLogin;

    /**
     * Ссылка на услугу.
     */
    @ManyToOne
    private ProvidedService providedService;

}
