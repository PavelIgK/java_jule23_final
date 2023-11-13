package ru.sberbank.jd.dto.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Data
public class UserDto {

    private UUID id;

    private String login;

    private String password;

    private boolean enabled;

    private String telegramId;

}
