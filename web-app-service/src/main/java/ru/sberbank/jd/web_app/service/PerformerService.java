package ru.sberbank.jd.web_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.PerformerDto;

import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.web_app.config.WebAppConfig;

@Service
@RequiredArgsConstructor
public class PerformerService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    public List<PerformerDto> findAllPerformers() {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
        return restTemplate.getForObject(uri, List.class);
    }

    public void savePerformer(PerformerDto performerDto) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
        performerDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(uri,performerDto,PerformerDto.class);
    }

    public void updatePerformer(PerformerDto performerDto) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
        restTemplate.put(uri+"/"+performerDto.getId(),performerDto);
    }

    public PerformerDto getPerformerById(String id) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
        System.out.println(restTemplate.getForObject(uri+"/"+id,String.class));
        return restTemplate.getForObject(uri+"/"+id,PerformerDto.class);
    }

    public void deletePerformerById(String id) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
        restTemplate.delete(uri+"/"+id);
    }

}
