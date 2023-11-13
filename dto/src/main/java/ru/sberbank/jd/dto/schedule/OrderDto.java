package ru.sberbank.jd.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Data
public class OrderDto {

    @NotBlank(message = "ID не может быть пустым.")
    private UUID id;

    private Date startDateTime;

    private Date endDateTime;

    private PerformerDto performer;

    private ClientDto client;

    private ProvidedServiceDto service;

}
