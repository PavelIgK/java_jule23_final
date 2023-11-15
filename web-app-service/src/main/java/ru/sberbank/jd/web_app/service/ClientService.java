package ru.sberbank.jd.web_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ClientDto;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final String uri = "http://schedule:8081/clients";
    private final RestTemplate restTemplate;

    public List<ClientDto> findAllClients() {

        //WebClient client = WebClient.builder().baseUrl(properties().getUrl()).build()
        //return client.get().retrieve().bodyToMono(String.class).block();
        return restTemplate.getForObject(uri, List.class);
    }

}
