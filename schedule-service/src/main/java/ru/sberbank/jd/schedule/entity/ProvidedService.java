package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность "Услуга".
 * Содержит список видов предоставляемых услуг.
 *
 */
@Entity
@Getter
@Setter
public class ProvidedService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Наименование услуги.
     */
    private String name;

    /**
     * Описание услуги.
     */
    private String description;

    /**
     * Длительность услуги, минуты.
     */
    private int duration;

    /**
     * Стоимость услуги, рубли.
     */
    private double price;

    /**
     * Список иполнителей услуги.
     */
    @OneToMany(mappedBy = "service")
    private Set<ServiceUser> serviceUsers;

}
