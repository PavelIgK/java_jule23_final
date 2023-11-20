package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.schedule.PerformerDto;

@Service
@RequiredArgsConstructor
public class PerformerService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/performers";
    }

    public List<PerformerDto> findAllPerformers() {
        return Arrays.asList(restTemplate.getForObject(getUri(), PerformerDto[].class));
    }

    public PerformerDto getPerformerById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,PerformerDto.class);
    }

}
