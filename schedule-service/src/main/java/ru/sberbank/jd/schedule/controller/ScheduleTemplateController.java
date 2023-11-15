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
import ru.sberbank.jd.dto.schedule.ScheduleTemplateDto;
import ru.sberbank.jd.schedule.service.ScheduleTemplateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule_templates")
public class ScheduleTemplateController {

    private final ScheduleTemplateService scheduleTemplateService;

    @PostMapping
    public ScheduleTemplateDto create(@RequestBody ScheduleTemplateDto scheduleTemplateDto) {
        return scheduleTemplateService.addScheduleTemplate(scheduleTemplateDto);
    }

    @PutMapping("/{id}")
    public ScheduleTemplateDto update(@PathVariable("id") String id, @RequestBody ScheduleTemplateDto scheduleTemplateDto) {
        return scheduleTemplateService.updateScheduleTemplate(scheduleTemplateDto);
    }

    @GetMapping("/{id}")
    public ScheduleTemplateDto get(@PathVariable("id") String id) {
        return scheduleTemplateService.getScheduleTemplateById(UUID.fromString(id));
    }

    @GetMapping
    public List<ScheduleTemplateDto> getAll() {
        return scheduleTemplateService.getAllScheduleTemplate();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        scheduleTemplateService.deleteScheduleTemplateById(UUID.fromString(id));
    }

}
