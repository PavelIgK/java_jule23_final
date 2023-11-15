package ru.sberbank.jd.dto.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDto {

    private UUID id;

    private String login;

    private String password;

    private boolean enabled;

    private String telegramId;

}
