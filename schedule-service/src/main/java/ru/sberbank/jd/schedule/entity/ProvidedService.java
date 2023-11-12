package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "service")
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
    @ManyToMany
    @JoinTable(name = "service_performer",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "performer_id", referencedColumnName = "id"))
    private Set<Performer> performers;


    @OneToMany(mappedBy = "service")
    private Set<Order> orders;

}
