package ru.sberbank.jd.web_app.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ScheduleDto;
import ru.sberbank.jd.web_app.config.WebAppConfig;

@Service
@RequiredArgsConstructor
public class ScheduleService {


    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/schedules";
    }

    public List<ScheduleDto> findAllSchedules() {
        return restTemplate.getForObject(getUri(), List.class);
    }

    public void saveSchedule(ScheduleDto scheduleDto) {
        scheduleDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(getUri(),scheduleDto,ScheduleDto.class);
    }

    public void updateSchedule(ScheduleDto scheduleDto) {
        restTemplate.put(getUri()+"/"+scheduleDto.getId(),scheduleDto);
    }

    public ScheduleDto getScheduleById(String id) {
        return restTemplate.getForObject(getUri()+"/"+id,ScheduleDto.class);
    }

    public void deleteScheduleById(String id) {
        restTemplate.delete(getUri()+"/"+id);
    }

}
