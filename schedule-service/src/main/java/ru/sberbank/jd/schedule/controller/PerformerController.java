package ru.sberbank.jd.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.schedule.service.PerformerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performers")
@Tag(name = "Сотрудники", description = "Взаимодействие с сотрудниками")
public class PerformerController {

    private final PerformerService performerService;

    @PostMapping
    @Operation(summary = "Добавить сотрудника")
    public PerformerDto create(@RequestBody PerformerDto performerDto) {
        return performerService.addPerformer(performerDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить сотрудника")
    public PerformerDto update(@PathVariable("id") String id, @RequestBody PerformerDto performerDto) {
        return performerService.updatePerformer(performerDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить сотрудника по UUID")
    public PerformerDto get(@PathVariable("id") String id) {
        return performerService.getPerformerById(UUID.fromString(id));
    }

    @GetMapping
    @Operation(summary = "Получить всех сотрудников")
    public List<PerformerDto> getAll() {
        return performerService.getAllPerformers();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить сотрудника по UUID")
    public void delete(@PathVariable("id") String id) {
        performerService.deletePerformerById(UUID.fromString(id));
    }


}
