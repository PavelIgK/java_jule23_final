package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ScheduleDto {

    private UUID id;

    private PerformerDto performer;

    private Date startDateTime;

    private Date endDateTime;

}
