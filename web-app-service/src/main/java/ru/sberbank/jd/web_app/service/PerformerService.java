package ru.sberbank.jd.web_app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.PerformerDto;

import java.util.List;
import java.util.UUID;

@Service
public class PerformerService {

    private final String uri = "http://schedule:8081/performers";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<PerformerDto> findAllPerformers() {
        return restTemplate.getForObject(uri, List.class);
    }

    public void savePerformer(PerformerDto performerDto) {
        performerDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(uri,performerDto,PerformerDto.class);
    }

    public void updatePerformer(PerformerDto performerDto) {
        restTemplate.put(uri+"/"+performerDto.getId(),performerDto);
    }

    public PerformerDto getPerformerById(String id) {
        System.out.println(restTemplate.getForObject(uri+"/"+id,String.class));
        return restTemplate.getForObject(uri+"/"+id,PerformerDto.class);
    }

    public void deletePerformerById(String id) {
        restTemplate.delete(uri+"/"+id);
    }

}
