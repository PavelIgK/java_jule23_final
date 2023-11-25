package ru.sberbank.jd.web_app.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ScheduleDto;
import ru.sberbank.jd.web_app.config.WebAppConfig;

/**
 * Сервис для получения инфо о графике работы.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {


    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/schedules";
    }

    /**
     * Получить список всех графиков.
     *
     * @return список всех графиков
     */
    public List<ScheduleDto> findAllSchedules() {
        return Arrays.asList(restTemplate.getForObject(getUri(), ScheduleDto[].class));
    }

    /**
     * Сохранить инфо о графике.
     *
     * @param scheduleDto инфо о графике
     */
    public void saveSchedule(ScheduleDto scheduleDto) {
        scheduleDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(getUri(),scheduleDto,ScheduleDto.class);
    }

    /**
     * Обновить инфо о графике.
     *
     * @param scheduleDto инфо о графике
     */
    public void updateSchedule(ScheduleDto scheduleDto) {
        restTemplate.put(getUri()+"/"+scheduleDto.getId(),scheduleDto);
    }

    /**
     * Получить инфо о графике работы по id.
     *
     * @param id id графика
     * @return инфо о графике
     */
    public ScheduleDto getScheduleById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,ScheduleDto.class);
    }

    /**
     * Удалить инфо о графике работы.
     *
     * @param id id графика
     */
    public void deleteScheduleById(String id) {
        restTemplate.delete(getUri()+"/"+id);
    }

}
