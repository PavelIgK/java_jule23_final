package ru.sberbank.jd.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.schedule.service.ProvidedServiceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
@Tag(name = "Предоставляемые услуги", description = "Взаимодействие с услугами")
public class ProvidedServiceController {

    private final ProvidedServiceService providedServiceService;

    @PostMapping
    @Operation(summary = "Добавить услугу")
    public ProvidedServiceDto create(@RequestBody ProvidedServiceDto providedServiceDto) {
        return providedServiceService.addService(providedServiceDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить услугу")
    public ProvidedServiceDto update(@PathVariable("id") String id, @RequestBody ProvidedServiceDto providedServiceDto) {
        return providedServiceService.updateService(providedServiceDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить услугу по UUID")
    public ProvidedServiceDto get(@PathVariable("id") String id) {
        return providedServiceService.getServicesById(UUID.fromString(id));
    }

    @GetMapping
    @Operation(summary = "Получить все услуги")
    public List<ProvidedServiceDto> getAll() {
        return providedServiceService.getAllServices();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить услугу по UUID")
    public void delete(@PathVariable("id") String id) {
        providedServiceService.deleteServiceById(UUID.fromString(id));
    }


}
