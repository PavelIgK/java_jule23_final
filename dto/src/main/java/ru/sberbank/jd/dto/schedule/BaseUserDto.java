package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class BaseUserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

}
