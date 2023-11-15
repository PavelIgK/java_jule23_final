package ru.sberbank.jd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProvidedServiceDto {

    private UUID id;

    private String name;

    private String description;

    private int duration;

    private double price;

}
