package ru.sberbank.jd.authorization.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность "Права доступа".
 * Содержит права доступа для пользователей.
 *
 */
@Entity
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Привелегия.
     */
    private String authority;

    /**
     * Список пользователей, обладающих привилегией.
     */
    @ManyToOne
    private User user;

}
