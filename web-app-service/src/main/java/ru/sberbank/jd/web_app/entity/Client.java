package ru.sberbank.jd.web_app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Client {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String telegramId;
    private String phoneNumber;

    public Client(String username, String firstName, String lastName, String telegramId, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telegramId = telegramId;
        this.phoneNumber = phoneNumber;
    }
}
