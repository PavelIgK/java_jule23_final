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

    private String login;

    private String firstName;

    private String lastName;

    private String password;

    private boolean enabled;

    private String telegramId;

    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private Set<Authority> authorities;
}
