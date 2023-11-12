package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import ru.sberbank.jd.authorization.entity.User;

/**
 * Сущность "Исполнитель".
 * Предназначена для хранения сотрудников, выполняющих услуги.
 * Связана с сущностью User (Пользователь) 1:1
 *
 */
@Entity
@Getter
@Setter
public class Performer extends BaseUser {

    /**
     * Пользователь (учетная запись), к которому привязан исполнитель.
     */
    @OneToOne
    private User user;


    /**
     * Список услуг, оказываемых данным пользователем.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "service_performer",
            joinColumns = @JoinColumn(name = "performer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<ProvidedService> providedServices;

    /**
     * Список заказов, оформленных на данного пользователя.
     */
    @OneToMany(mappedBy = "performer")
    private Set<Order> orders;

    /**
     * Список расписаний, привязанных к данному пользователю.
     */
    @OneToMany(mappedBy = "performer")
    private Set<Schedule> schedules;

    /**
     * Список шаблонов расписаний, привязанных к данному пользователю.
     */
    @OneToMany(mappedBy = "performer")
    private Set<ScheduleTemplate> scheduleTemplates;

}
