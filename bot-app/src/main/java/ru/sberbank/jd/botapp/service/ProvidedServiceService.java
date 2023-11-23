package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

@Service
@RequiredArgsConstructor
public class ProvidedServiceService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/services";
    }

    public List<ProvidedServiceDto> findAllServices() {
        return Arrays.asList(restTemplate.getForObject(getUri(), ProvidedServiceDto[].class));
    }

    public ProvidedServiceDto getServicesById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,ProvidedServiceDto.class);
    }

}
