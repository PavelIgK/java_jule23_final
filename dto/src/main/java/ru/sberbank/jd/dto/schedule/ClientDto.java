package ru.sberbank.jd.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.sberbank.jd.dto.authorization.UserDto;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ClientDto {

    @NotBlank(message = "ID не может быть пустым.")
    private UUID id;

    @NotBlank(message = "Имя не может быть пустым.")
    @Size(max = 50, message = "Максимальная длина имени 50.")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой.")
    @Size(max = 100, message = "Максимальная длина фамилии 100.")
    private String lastName;

    private String phoneNumber;

    private UserDto user;

}
