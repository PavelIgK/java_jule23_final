package ru.sberbank.jd.dto.authorization;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthorityDto {

    private UUID id;

    private String authority;

    private UserDto user;

}
