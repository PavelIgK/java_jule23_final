package ru.sberbank.jd.web_app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ClientDto;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ClientService {


    public List<ClientDto> findAllClients() {
        final String uri = "http://localhost:8081/clients";

        RestTemplate restTemplate = new RestTemplate();
        //WebClient client = WebClient.builder().baseUrl(properties().getUrl()).build()
        //return client.get().retrieve().bodyToMono(String.class).block();
        return restTemplate.getForObject(uri, List.class);
    }

}
