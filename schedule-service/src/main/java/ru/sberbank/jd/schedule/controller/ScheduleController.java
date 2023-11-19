package ru.sberbank.jd.schedule.controller;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.jd.dto.schedule.ScheduleDto;
import ru.sberbank.jd.schedule.service.ScheduleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
@Tag(name = "Расписания сотрудников", description = "Взаимодействие с расписаниями")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "Добавить расписание")
    public ScheduleDto create(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.addSchedule(scheduleDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить расписание")
    public ScheduleDto update(@PathVariable("id") String id, @RequestBody ScheduleDto scheduleDto) {
        return scheduleService.updateSchedule(scheduleDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить расписание по UUID")
    public ScheduleDto get(@PathVariable("id") String id) {
        return scheduleService.getScheduleById(UUID.fromString(id));
    }

    @GetMapping
    @Operation(summary = "Получить все расписания")
    public List<ScheduleDto> getAll() {
        return scheduleService.getAllSchedule();
    }

    @GetMapping("/performer/{id}")
    @Operation(summary = "Получить расписания по UUID сотрудника")
    public List<ScheduleDto> getScheduleByPerformerId(@PathVariable("id") String id) {
        return scheduleService.getScheduleByPerformerId(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить расписание по UUID")
    public void delete(@PathVariable("id") String id) {
        scheduleService.deleteScheduleById(UUID.fromString(id));
    }

}
