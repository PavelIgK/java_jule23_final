package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.schedule.TimeSlotDto;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/timeslots";
    }

    public List<TimeSlotDto> getTimeSlots(String performerId, String dateStr, String serviceId) {
        return Arrays.asList(restTemplate.getForObject(
                String.format(getUri()+"/?performer_id=%s&date=%s&service_id=%s",performerId, dateStr, serviceId),
                TimeSlotDto[].class));
    }

}
