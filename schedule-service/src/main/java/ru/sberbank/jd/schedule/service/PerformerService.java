package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.PerformerDto;
import ru.sberbank.jd.schedule.entity.Client;
import ru.sberbank.jd.schedule.entity.Performer;
import ru.sberbank.jd.schedule.repository.PerformerRepository;
import ru.sberbank.jd.schedule.utils.MappingUtils;

/**
 * Сервис для работы с исполнителями услуг.
 *
 */
@Service
@RequiredArgsConstructor
public class PerformerService {

    private final PerformerRepository performerRepository;

    private final MappingUtils mappingUtils;


    /**
     * Получить список всех исполнителей.
     *
     * @return - список исполнителей
     */
    public List<PerformerDto> getAllPerformers() {
        return performerRepository
                .findAll()
                .stream()
                .map(mappingUtils::mapToPerformerDto)
                .toList();
    }

    /**
     * Получить данные исполнителя по его id.
     *
     * @param id - id исполнителя
     * @return - исполнитель
     */
    public PerformerDto getPerformerById(UUID id) {
        return mappingUtils.mapToPerformerDto(performerRepository.findById(id).get());
    }

    /**
     * Добавить нового исполнителя.
     *
     * @param performerDto - исполнитель
     * @return - исполнитель
     */
    public PerformerDto addPerformer(PerformerDto performerDto) {
        performerDto.setId(UUID.randomUUID());
        performerRepository.save(mappingUtils.mapToPerformerEntity(performerDto));
        return performerDto;
    }

    /**
     * Обновить исполнителя.
     *
     * @param performerDto - исполнитель
     * @return - исполнитель
     */
    public PerformerDto updatePerformer(PerformerDto performerDto) {
        performerRepository.save(mappingUtils.mapToPerformerEntity(performerDto));
        return performerDto;
    }

    /**
     * Удалить исполнителя.
     *
     * @param performerDto - исполнитель
     */
    public void deletePerformer(PerformerDto performerDto) {
        performerRepository.delete(mappingUtils.mapToPerformerEntity(performerDto));
    }

}
