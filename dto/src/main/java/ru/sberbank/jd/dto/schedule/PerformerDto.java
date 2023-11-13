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

    private Set<ProvidedServiceDto> providedServices;

    private Set<OrderDto> orders;

    private Set<ScheduleDto> schedules;

    private Set<ScheduleTemplateDto> scheduleTemplates;
}
