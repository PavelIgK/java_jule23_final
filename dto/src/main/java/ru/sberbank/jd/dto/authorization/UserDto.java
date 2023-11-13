package ru.sberbank.jd.dto.authorization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@AllArgsConstructor
@Data
public class UserDto {

    @NotBlank(message = "ID не может быть пустым.")
    private UUID id;

    private String login;

    private String password;

    private boolean enabled;

    @Positive(message = "Продолжительность должна быть положительной")
    private String telegramId;

}
