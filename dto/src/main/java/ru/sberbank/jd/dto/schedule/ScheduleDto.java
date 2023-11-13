package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ScheduleDto {

    private UUID id;

    private PerformerDto performer;

    private Date startDateTime;

    private Date endDateTime;

}
