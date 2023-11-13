package ru.sberbank.jd.dto.schedule;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ProvidedServiceDto {

    @NotBlank(message = "ID не может быть пустым.")
    private UUID id;

    @NotBlank(message = "Имя не может быть пустым.")
    @Size(max = 50, message = "Максимальная длина имени 50.")
    private String name;

    @NotBlank(message = "Описание не может быть пустым.")
    @Size(max = 100, message = "Максимальная длина имени 100.")
    private String description;

    @Positive(message = "Продолжительность должна быть положительной")
    @Max(value=720)
    private int duration;

    @Positive(message = "Цена должна быть положительной")
    private double price;

}
