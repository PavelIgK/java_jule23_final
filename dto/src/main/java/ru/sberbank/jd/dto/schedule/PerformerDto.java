package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sberbank.jd.dto.authorization.UserDto;
import java.util.UUID;

@NoArgsConstructor
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
