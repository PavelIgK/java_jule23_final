package ru.sberbank.jd.authorization.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность "Пользователь".
 * Хранит данные учетной записи.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Логин пользователя.
     */
    private String login;

    /**
     * Имя пользователя.
     */
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Признак актвности учетной записи пользователя.
     */
    private boolean enabled;

    /**
     * id пользователя в Telegram.
     */
    private String telegramId;

    /**
     * Номер телефона пользователя.
     */
    private String phoneNumber;

    /**
     * Список привелегий пользователя.
     */
    @OneToMany(mappedBy = "user")
    private Set<Authority> authorities;

}
