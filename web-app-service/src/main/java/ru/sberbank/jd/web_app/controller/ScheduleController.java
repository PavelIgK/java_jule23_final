package ru.sberbank.jd.web_app.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.dto.schedule.ScheduleDto;
import ru.sberbank.jd.web_app.service.PerformerService;
import ru.sberbank.jd.web_app.service.ScheduleService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final PerformerService performerService;

    @GetMapping
    public String getAllSchedules(Model model) {
        List<ScheduleDto> schedulesDtoList = scheduleService.findAllSchedules();
        model.addAttribute("schedules", schedulesDtoList);
        return "schedules";
    }

    @GetMapping("/create")
    public String showCreateScheduleForm(Model model) {
        model.addAttribute("schedule", ScheduleDto.builder().build());
        model.addAttribute("performers", performerService.findAllPerformers());
        return "schedule_create";
    }

    @PostMapping
    public String createSchedule(@ModelAttribute("schedule") ScheduleDto scheduleDto) {
        PerformerDto performerDto = performerService.getPerformerById(scheduleDto.getPerformer().getId().toString());
        scheduleDto.setPerformer(performerDto);
        scheduleService.saveSchedule(scheduleDto);
        return "redirect:/schedules";
    }

    @GetMapping("/update/{id}")
    public String showUpdateScheduleForm(@PathVariable(value = "id") String id, Model model) {
        ScheduleDto scheduleDto = scheduleService.getScheduleById(id);
        PerformerDto performerDto = performerService.getPerformerById(scheduleDto.getPerformer().getId().toString());
        model.addAttribute("schedule", scheduleDto);
        model.addAttribute("performers", performerService.findAllPerformers());
        return "schedule_update";
    }

    @PostMapping("/update")
    public String updateSchedule(@ModelAttribute("schedule") ScheduleDto scheduleDto) {
        PerformerDto performerDto = performerService.getPerformerById(scheduleDto.getPerformer().getId().toString());
        scheduleDto.setPerformer(performerDto);
        scheduleService.updateSchedule(scheduleDto);
        return "redirect:/schedules";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable(value = "id") String id) {
        scheduleService.deleteScheduleById(id);
        return "redirect:/schedules";
    }

}
