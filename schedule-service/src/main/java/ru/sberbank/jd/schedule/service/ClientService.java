package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.Client;
import ru.sberbank.jd.schedule.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).get();
    }

    public Client addClient(Client client) {
        client.setId(UUID.randomUUID());
        return clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(getClientById(client.getId()));
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }

}
