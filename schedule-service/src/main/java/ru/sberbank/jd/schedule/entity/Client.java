package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import ru.sberbank.jd.authorization.entity.User;

/**
 * Сущность "Клиент".
 * Предназначена для хранения клиентов, заказывающих услуги.
 * Связана с сущностью User (Пользователь) 1:1
 *
 */
@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Пользователь (учетная запись), к которому привязан клиент.
     */
    @OneToOne
    private User user;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Номер телефона.
     */
    private String phoneNumber;

    /**
     * Список заказов, сделанных данным пользователем.
     */
    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

}
