package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.UUID;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {

    private UUID id;

    private Date startDateTime;

    private Date endDateTime;

    private PerformerDto performer;

    private ClientDto client;

    private ProvidedServiceDto service;

}
