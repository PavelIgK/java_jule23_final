package ru.sberbank.jd.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ScheduleTemplateDto {

    @NotBlank(message = "ID не может быть пустым.")
    private UUID id;

    @NotBlank(message = "Наименование расписания не может быть пустым.")
    @Size(max = 100, message = "Максимальная длина описания 100.")
    private String scheduleName;

    private PerformerDto performer;


    private LocalTime startTime;

    private LocalTime endTime;
}
