package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class ScheduleTemplateDto {

    private UUID id;

    private String scheduleName;

    private PerformerDto performer;


    private LocalTime startTime;

    private LocalTime endTime;
}
