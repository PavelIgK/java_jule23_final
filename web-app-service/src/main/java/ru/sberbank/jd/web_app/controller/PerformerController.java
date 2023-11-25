package ru.sberbank.jd.web_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.web_app.service.ClientService;
import ru.sberbank.jd.web_app.service.PerformerService;

import java.util.List;

/**
 * Контроллер для обработки запросов по сотрудникам.
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/performers")
public class PerformerController {

    private final PerformerService performerService;
    private final ClientService clientService;

    /**
     * Получить инфо по всем сотрудникам.
     *
     * @param model модель
     * @return шаблон со списком всех сотрудников
     */
    @GetMapping
    public String getAllPerformers(Model model) {
        List<PerformerDto> performerDtoList = performerService.findAllPerformers();
        model.addAttribute("performers", performerDtoList);
        return "performers";
    }

    /**
     * Отобразить страницу для создания записи о новом сотруднике.
     *
     * @param model  модель
     * @return шаблон для создания нового сотрудника
     */
    @GetMapping("/create")
    public String showCreatePerformerForm(Model model) {
        model.addAttribute("performer", PerformerDto.builder().build());
        return "performer_create";
    }

    /**
     * Сделать клиента сотрудником.
     *
     * @param id id клиента
     * @param model модель
     * @return шаблон для создания записи о новом сотруднике
     */
    @GetMapping("/create_of_client/{id}")
    public String showCreatePerformerOfClientForm(@PathVariable (value = "id") String id, Model model) {
        ClientDto clientDto = clientService.getClientById(id);
        PerformerDto performerDto = PerformerDto.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phoneNumber(clientDto.getPhoneNumber())
                .user(clientDto.getUser())
                .build();
        model.addAttribute("performer", performerDto);
        return "performer_create";
    }

    /**
     * Создать сотрудника.
     *
     * @param performerDto инфо о сотруднике
     * @return шаблон со списком всех сотрудников
     */
    @PostMapping
    public String createPerformer(@ModelAttribute("performer") PerformerDto performerDto) {
        performerService.savePerformer(performerDto);
        return "redirect:/performers";
    }

    /**
     * Обновить инфо о сотруднике.
     *
     * @param performerDto  инфо о сотруднике
     * @return шаблон со списком всех сотрудников
     */
    @PostMapping("/update")
    public String updatePerformer(@ModelAttribute("performer") PerformerDto performerDto) {
        performerService.updatePerformer(performerDto);
        return "redirect:/performers";
    }

    /**
     * Отобразить страницу для редактирования инфо о сотруднике.
     *
     * @param id id сотрудника
     * @param model модель
     * @return шаблон для редактирования инфо о сотруднике
     */
    @GetMapping("/update/{id}")
    public String showUpdatePerformerForm(@PathVariable (value = "id") String id, Model model) {
        PerformerDto performerDto = performerService.getPerformerById(id);
        model.addAttribute("performer", performerDto);
        return "performer_update";
    }

    /**
     * Удалить сотрудника.
     *
     * @param id id сотрудника
     * @return шаблон со списком всех сотрудников
     */
    @GetMapping("/delete/{id}")
    public String deletePerformer(@PathVariable(value = "id") String id) {
        performerService.deletePerformerById(id);
        return "redirect:/performers";
    }
}
