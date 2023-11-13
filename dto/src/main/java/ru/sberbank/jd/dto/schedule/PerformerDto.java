package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.sberbank.jd.dto.authorization.UserDto;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Data
public class PerformerDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private UserDto user;

}
