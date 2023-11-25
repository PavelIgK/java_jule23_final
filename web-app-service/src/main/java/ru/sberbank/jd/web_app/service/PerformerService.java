package ru.sberbank.jd.web_app.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.web_app.config.WebAppConfig;

/**
 * Сервис для получения инфо о сотрудниках.
 */
@Service
@RequiredArgsConstructor
public class PerformerService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/performers";
    }

    /**
     * Получить всех сотрудников.
     *
     * @return список всех сотрудников
     */
    public List<PerformerDto> findAllPerformers() {
        return Arrays.asList(restTemplate.getForObject(getUri(), PerformerDto[].class));
    }

    /**
     * Сохранить инфо о сотруднике.
     *
     * @param performerDto инфо о сотруднике
     */
    public void savePerformer(PerformerDto performerDto) {
        performerDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(getUri(),performerDto,PerformerDto.class);
    }

    /**
     * Обновить инфо о сотруднике.
     *
     * @param performerDto инфо о сотруднике
     */
    public void updatePerformer(PerformerDto performerDto) {
        restTemplate.put(getUri()+"/"+performerDto.getId(),performerDto);
    }

    /**
     * Получить инфо о сотруднике по id.
     *
     * @param id id сотрудника
     * @return инфо о сотруднике
     */
    public PerformerDto getPerformerById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,PerformerDto.class);
    }

    /**
     * Удалить инфо о сотруднике.
     *
     * @param id id сотрудника
     */
    public void deletePerformerById(String id) {
        restTemplate.delete(getUri()+"/"+id);
    }

}
