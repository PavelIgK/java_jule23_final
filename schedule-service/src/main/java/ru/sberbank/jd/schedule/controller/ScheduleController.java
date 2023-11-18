package ru.sberbank.jd.schedule.controller;

import java.util.List;
import java.util.UUID;
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
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleDto create(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.addSchedule(scheduleDto);
    }

    @PutMapping("/{id}")
    public ScheduleDto update(@PathVariable("id") String id, @RequestBody ScheduleDto scheduleDto) {
        return scheduleService.updateSchedule(scheduleDto);
    }

    @GetMapping("/{id}")
    public ScheduleDto get(@PathVariable("id") String id) {
        return scheduleService.getScheduleById(UUID.fromString(id));
    }

    @GetMapping
    public List<ScheduleDto> getAll() {
        return scheduleService.getAllSchedule();
    }

    @GetMapping("/performer/{id}")
    public List<ScheduleDto> getScheduleByPerformerId(@PathVariable("id") String id) {
        return scheduleService.getScheduleByPerformerId(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        scheduleService.deleteScheduleById(UUID.fromString(id));
    }

}
