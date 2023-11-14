package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.schedule.entity.Client;
import ru.sberbank.jd.schedule.repository.ClientRepository;

/**
 * Сервис для работы с клиентами.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(Client::toDto).toList();
    }

    public ClientDto getClientById(UUID id) {
        return clientRepository.findById(id).get().toDto();
    }

    public ClientDto addClient(ClientDto clientDto) {
        Client client = Client.of(clientDto);
        client.setId(UUID.randomUUID());
        return clientRepository.save(client).toDto();
    }

    public ClientDto updateClient(ClientDto clientDto) {
        return clientRepository.save(Client.of(clientDto)).toDto();
    }

    public void deleteClient(ClientDto clientDto) {
        clientRepository.delete(Client.of(clientDto));
    }

    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }

}
