package ru.sberbank.jd.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.schedule.service.ProvidedServiceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ProvidedService {

    private final ProvidedServiceService providedServiceService;

    @PostMapping
    public ProvidedServiceDto create(@RequestBody ProvidedServiceDto providedServiceDto) {
        return providedServiceService.addService(providedServiceDto);
    }

    @PutMapping("/{id}")
    public ProvidedServiceDto update(@PathVariable("id") String id, @RequestBody ProvidedServiceDto providedServiceDto) {
        return providedServiceService.updateService(providedServiceDto);
    }

    @GetMapping("/{id}")
    public ProvidedServiceDto get(@PathVariable("id") String id) {
        return providedServiceService.getServicesById(UUID.fromString(id));
    }

    @GetMapping
    public List<ProvidedServiceDto> getAll() {
        return providedServiceService.getAllServices();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        providedServiceService.deleteServiceById(UUID.fromString(id));
    }


}
