package ru.sberbank.jd.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.TimeSlotDto;
import ru.sberbank.jd.schedule.service.TimeSlotService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/timeslots")
@RequiredArgsConstructor
@Tag(name = "Таймслоты", description = "Взаимодействие с таймслотами")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;


    @GetMapping
    @Operation(summary = "Получить свободные таймслоты")
    public List<TimeSlotDto> getFreeSLots(@RequestParam("performer_id") String performerId,
                                          @RequestParam("date") String dateStr,
                                          @RequestParam("service_id") String serviceId) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            date = LocalDate.now();
        }
        return timeSlotService.getFreeTimeSlots(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                UUID.fromString(performerId), UUID.fromString(serviceId));
    }

}
