package ru.sberbank.jd.web_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ClientDto;

import java.util.List;
import ru.sberbank.jd.web_app.config.WebAppConfig;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    public List<ClientDto> findAllClients() {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/clients";
        return restTemplate.getForObject(uri, List.class);
    }

}
