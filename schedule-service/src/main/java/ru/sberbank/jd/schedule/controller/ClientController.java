package ru.sberbank.jd.schedule.controller;


import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.schedule.service.ClientService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Component
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ClientDto create(@RequestBody ClientDto clientDto) {
        return clientService.addClient(clientDto);
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable("id") String id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientDto);
    }

    @GetMapping("/{id}")
    public ClientDto get(@PathVariable("id") String id) {
        return clientService.getClientById(UUID.fromString(id));
    }

    @GetMapping("/")
    public List<ClientDto> getAll() {
        return clientService.getAllClients();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        clientService.deleteClientById(UUID.fromString(id));
    }


}
