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

/**
 * Контроллер для обработки запросов о графике работы.
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final PerformerService performerService;

    /**
     * Получить все расписания.
     *
     * @param model модель
     * @return шаблон со списком всех графиков работы
     */
    @GetMapping
    public String getAllSchedules(Model model) {
        List<ScheduleDto> schedulesDtoList = scheduleService.findAllSchedules();
        model.addAttribute("schedules", schedulesDtoList);
        return "schedules";
    }

    /**
     * Отобразить страницу для создания нового графика.
     *
     * @param model модель
     * @return шаблон для создания нового графика
     */
    @GetMapping("/create")
    public String showCreateScheduleForm(Model model) {
        model.addAttribute("schedule", ScheduleDto.builder().build());
        model.addAttribute("performers", performerService.findAllPerformers());
        return "schedule_create";
    }

    /**
     * Создать новый график.
     *
     * @param scheduleDto инфо о графике
     * @return шаблон со списком всех графиков
     */
    @PostMapping
    public String createSchedule(@ModelAttribute("schedule") ScheduleDto scheduleDto) {
        PerformerDto performerDto = performerService.getPerformerById(scheduleDto.getPerformer().getId().toString());
        scheduleDto.setPerformer(performerDto);
        scheduleService.saveSchedule(scheduleDto);
        return "redirect:/schedules";
    }

    /**
     * Отобразить страницу для редактирования графика работы.
     *
     * @param id id графика
     * @param model модель
     * @return шаблон для редактирования графика работы
     */
    @GetMapping("/update/{id}")
    public String showUpdateScheduleForm(@PathVariable(value = "id") String id, Model model) {
        ScheduleDto scheduleDto = scheduleService.getScheduleById(id);
        PerformerDto performerDto = performerService.getPerformerById(scheduleDto.getPerformer().getId().toString());
        model.addAttribute("schedule", scheduleDto);
        model.addAttribute("performers", performerService.findAllPerformers());
        return "schedule_update";
    }

    /**
     * Обновить инфо о графике работы.
     *
     * @param scheduleDto инфо о графике
     * @return шаблон со списком всех графиков
     */
    @PostMapping("/update")
    public String updateSchedule(@ModelAttribute("schedule") ScheduleDto scheduleDto) {
        PerformerDto performerDto = performerService.getPerformerById(scheduleDto.getPerformer().getId().toString());
        scheduleDto.setPerformer(performerDto);
        scheduleService.updateSchedule(scheduleDto);
        return "redirect:/schedules";
    }

    /**
     * Удалить график работы.
     *
     * @param id id графика
     * @return шаблон со списком всех графиков
     */
    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable(value = "id") String id) {
        scheduleService.deleteScheduleById(id);
        return "redirect:/schedules";
    }

}
