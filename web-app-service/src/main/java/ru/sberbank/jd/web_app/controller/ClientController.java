package ru.sberbank.jd.web_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.web_app.service.ClientService;

import java.util.List;

/**
 * Контроллер для обработки запросов о клиентах.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    /**
     * Получить инфо по всем клиентам.
     *
     * @param model модель
     * @return шаблон со списком всех клиентов
     */
    @GetMapping
    public String getAllClients(Model model) {
        List<ClientDto> clientList = clientService.findAllClients();
        model.addAttribute("clients", clientList);
        return "clients";
    }


}
