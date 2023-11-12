package ru.sberbank.jd.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

}
