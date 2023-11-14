package ru.sberbank.jd.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.web_app.entity.Client;
import ru.sberbank.jd.web_app.entity.Performer;
import ru.sberbank.jd.web_app.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String getAllClients(Model model) {
        List<ClientDto> clientList = clientService.findAllClients();
        model.addAttribute("clients", clientList);
        return "clients";
    }


}
