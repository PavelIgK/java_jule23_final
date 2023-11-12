package ru.sberbank.jd.web_app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ServiceDTO {

    private String id;
    private String name;
    private BigDecimal price;
    private int duration;
    private String description;

    public ServiceDTO(String name, BigDecimal price, int duration, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
    }
}
