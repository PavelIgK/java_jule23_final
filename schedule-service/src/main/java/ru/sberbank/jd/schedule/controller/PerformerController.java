package ru.sberbank.jd.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.schedule.service.PerformerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performers")
public class PerformerController {

    private final PerformerService performerService;

    @PostMapping
    public PerformerDto create(@RequestBody PerformerDto performerDto) {
        return performerService.addPerformer(performerDto);
    }

    @PutMapping("/{id}")
    public PerformerDto update(@PathVariable("id") String id, @RequestBody PerformerDto performerDto) {
        return performerService.updatePerformer(performerDto);
    }

    @GetMapping("/{id}")
    public PerformerDto get(@PathVariable("id") String id) {
        return performerService.getPerformerById(UUID.fromString(id));
    }

    @GetMapping
    public List<PerformerDto> getAll() {
        return performerService.getAllPerformers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        performerService.deletePerformerById(UUID.fromString(id));
    }


}
