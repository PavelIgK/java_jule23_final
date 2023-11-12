package ru.sberbank.jd.web_app.service;

import org.springframework.stereotype.Service;
import ru.sberbank.jd.web_app.entity.Client;
import ru.sberbank.jd.web_app.repository.ClientRepository;
import ru.sberbank.jd.web_app.repository.PerformerRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

}
