package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

/**
 * Сервис для получения возможных услуг.
 *
 */
@Service
@RequiredArgsConstructor
public class ProvidedServiceService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/services";
    }

    /**
     * Получение списка всех услугу.
     *
     * @return List<ProvidedServiceDto>
     */
    public List<ProvidedServiceDto> findAllServices() {
        return Arrays.asList(restTemplate.getForObject(getUri(), ProvidedServiceDto[].class));
    }

    /**
     * Получение услуги по id
     * @param id uuid услуги.
     * @return ProvidedServiceDto
     */
    public ProvidedServiceDto getServicesById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,ProvidedServiceDto.class);
    }

}
