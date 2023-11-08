package ru.sberbank.jd.web_app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Performer {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isEnabled;
    private String telegramId;
    private String phoneNumber;

    public Performer(String username, String firstName, String lastName, Boolean isEnabled, String telegramId, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEnabled = isEnabled;
        this.telegramId = telegramId;
        this.phoneNumber = phoneNumber;
    }
}
