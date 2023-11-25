package ru.sberbank.jd.web_app.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ClientDto;
import java.util.List;
import ru.sberbank.jd.web_app.config.WebAppConfig;

/**
 * Сервис для получения инфо о клиентах.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/clients";
    }

    /**
     * Получить всех клиентов.
     *
     * @return список клиентов
     */
    public List<ClientDto> findAllClients() {
        return Arrays.asList(restTemplate.getForObject(getUri(), ClientDto[].class));
    }

    /**
     * Получить инфо о клиенте по id
     *
     * @param id id клиента
     * @return инфо о клиенте
     */
    public ClientDto getClientById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,ClientDto.class);
    }

}
