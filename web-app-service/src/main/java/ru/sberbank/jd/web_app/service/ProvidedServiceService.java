package ru.sberbank.jd.web_app.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.web_app.config.WebAppConfig;

/**
 * Сервис для получения инфо о предоставляемых услугах.
 */
@Service
@RequiredArgsConstructor
public class ProvidedServiceService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
    }

    /**
     * Получить список всех услуг.
     *
     * @return список всех услуг
     */
    public List<ProvidedServiceDto> findAllServices() {
        return Arrays.asList(restTemplate.getForObject(getUri(), ProvidedServiceDto[].class));
    }

    /**
     * Сохранить инфо об услуге.
     *
     * @param serviceDto инфо об услуге
     */
    public void saveService(ProvidedServiceDto serviceDto) {
        serviceDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(getUri(), serviceDto, ProvidedServiceDto.class);
    }

    /**
     * Обновить инфо об услуге.
     *
     * @param serviceDto инфо об услуге
     */
    public void updateService(ProvidedServiceDto serviceDto) {
        restTemplate.put(getUri() + "/" + serviceDto.getId(), serviceDto);
    }

    /**
     * Получить инфо об услуге по id.
     *
     * @param id id услуги
     * @return инфо об услуге
     */
    public ProvidedServiceDto getServiceById(String id) {
        return restTemplate.getForObject(getUri() + "/" + id, ProvidedServiceDto.class);
    }

    /**
     * Удалить инфо об услуге по id.
     *
     * @param id id услуги
     */
    public void deleteServiceById(String id) {
        restTemplate.delete(getUri() + "/" + id);
    }

}
