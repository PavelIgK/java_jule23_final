package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.sberbank.jd.dto.authorization.UserDto;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class PerformerDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private UserDto user;

}
