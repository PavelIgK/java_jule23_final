package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.*;

import java.util.Set;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.sberbank.jd.authorization.entity.User;
import ru.sberbank.jd.dto.schedule.PerformerDto;

/**
 * Сущность "Исполнитель".
 * Предназначена для хранения сотрудников, выполняющих услуги.
 * Связана с сущностью User (Пользователь) 1:1
 *
 */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * Метод получения DTO из entity.
     *
     * @return DTO объект
     */
    public PerformerDto toDto() {
        return PerformerDto.builder()
                .id(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .phoneNumber(this.getPhoneNumber())
                .user(this.getUser().toDto())
                .build();
    }

    /**
     * Метод получения entity из DTO.
     *
     * @param performerDto DTO
     * @return entity
     */
    public static Performer of(PerformerDto performerDto) {
        return Performer.builder()
                .firstName(performerDto.getFirstName())
                .lastName(performerDto.getLastName())
                .phoneNumber(performerDto.getPhoneNumber())
                .user(User.of(performerDto.getUser()))
                .build();
    }

}
