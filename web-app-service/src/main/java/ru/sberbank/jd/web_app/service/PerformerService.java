package ru.sberbank.jd.web_app.service;

import java.util.Arrays;
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

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
    }

    public List<PerformerDto> findAllPerformers() {
        return Arrays.asList(restTemplate.getForObject(getUri(), PerformerDto[].class));
    }

    public void savePerformer(PerformerDto performerDto) {
        performerDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(getUri(),performerDto,PerformerDto.class);
    }

    public void updatePerformer(PerformerDto performerDto) {
        restTemplate.put(getUri()+"/"+performerDto.getId(),performerDto);
    }

    public PerformerDto getPerformerById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,PerformerDto.class);
    }

    public void deletePerformerById(String id) {
        restTemplate.delete(getUri()+"/"+id);
    }

}
