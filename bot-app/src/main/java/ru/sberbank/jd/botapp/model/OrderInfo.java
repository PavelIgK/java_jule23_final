package ru.sberbank.jd.botapp.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import ru.sberbank.jd.dto.authorization.UserDto;
import ru.sberbank.jd.dto.schedule.ClientDto;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

@Data
@Builder
public class OrderInfo {

    private String clientId;
    private String performerId;
    private String serviceId;
    private String date;
    private LocalDateTime startDateTime;
    private ClientDto client;
    private PerformerDto performer;
    private ProvidedServiceDto service;


}
