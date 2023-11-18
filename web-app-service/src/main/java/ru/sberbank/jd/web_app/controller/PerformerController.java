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

@Controller
@RequiredArgsConstructor
@RequestMapping("/performers")
public class PerformerController {

    private final PerformerService performerService;
    private final ClientService clientService;

    @GetMapping
    public String getAllPerformers(Model model) {
        List<PerformerDto> performerDtoList = performerService.findAllPerformers();
        model.addAttribute("performers", performerDtoList);
        return "performers";
    }

    @GetMapping("/create")
    public String showCreatePerformerForm(Model model) {
        model.addAttribute("performer", PerformerDto.builder().build());
        return "performer_create";
    }

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

    @PostMapping
    public String createPerformer(@ModelAttribute("performer") PerformerDto performerDto) {
        performerService.savePerformer(performerDto);
        return "redirect:/performers";
    }

    @PostMapping("/update")
    public String updatePerformer(@ModelAttribute("performer") PerformerDto performerDto) {
        System.out.println(performerDto);
        return "redirect:/performers";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePerformerForm(@PathVariable (value = "id") String id, Model model) {
        PerformerDto performerDto = performerService.getPerformerById(id);
        model.addAttribute("performer", performerDto);
        return "performer_update";
    }

    @GetMapping("/delete/{id}")
    public String deletePerformer(@PathVariable(value = "id") String id) {
        performerService.deletePerformerById(id);
        return "redirect:/performers";
    }
}
