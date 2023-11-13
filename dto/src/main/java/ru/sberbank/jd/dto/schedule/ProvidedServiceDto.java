package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ProvidedServiceDto {

    private UUID id;

    private String name;

    private String description;

    private int duration;

    private double price;

}
