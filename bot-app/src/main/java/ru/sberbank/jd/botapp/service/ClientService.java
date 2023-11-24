package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.PerformerDto;

/**
 * Сервис для получения клиентов.
 *
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/clients";
    }

    /**
     * Получение всех клиентов.
     *
     * @return List<ClientDto>
     */
    public List<ClientDto> findAllClients() {
        return Arrays.asList(restTemplate.getForObject(getUri(), ClientDto[].class));
    }

    /**
     * Получение клиента по uuid.
     *
     * @param id uuid.
     * @return ClientDto
     */
    public ClientDto getClientById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,ClientDto.class);
    }

    /**
     * Получение клиента по uuid его юзера.
     * @param id uuid
     * @return ClientDto
     */
    public ClientDto getClientByUserId(String id) {
        return restTemplate.getForObject(getUri()+"/user/"+id,ClientDto.class);
    }

}
