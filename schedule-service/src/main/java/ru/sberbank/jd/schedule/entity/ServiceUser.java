package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Вспомогательная сущность для связи сущностей "Услуга" и "Пользователь".
 *
 */
@Entity
@Getter
@Setter
public class ServiceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Ссылка на услугу.
     */
    @ManyToOne
    private Service service;

    /**
     * Id исполнителя услуг.
     */
    private UUID userId;

    /**
     * Логин исполнителя услуг.
     */
    private String login;
}
