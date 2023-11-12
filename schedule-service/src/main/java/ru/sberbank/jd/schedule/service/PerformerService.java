package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.Performer;
import ru.sberbank.jd.schedule.repository.PerformerRepository;

/**
 * Сервис для работы с исполнителями услуг.
 *
 */
@Service
@RequiredArgsConstructor
public class PerformerService {

    private final PerformerRepository performerRepository;

    /**
     * Получить список всех исполнителей.
     *
     * @return - список исполнителей
     */
    public List<Performer> getAllPerformers() {
        return performerRepository.findAll();
    }

    /**
     * Получить данные исполнителя по его id.
     *
     * @param id - id исполнителя
     * @return - исполнитель
     */
    public Performer getPerformerById(UUID id) {
        return performerRepository.findById(id).get();
    }

    /**
     * Добавить нового исполнителя.
     *
     * @param performer - исполнитель
     * @return - исполнитель
     */
    public Performer addPerformer(Performer performer) {
        performer.setId(UUID.randomUUID());
        return performerRepository.save(performer);
    }

    /**
     * Обновить исполнителя.
     *
     * @param performer - исполнитель
     * @return - исполнитель
     */
    public Performer updatePerformer(Performer performer) {
        return performerRepository.save(getPerformerById(performer.getId()));
    }

    /**
     * Удалить исполнителя.
     *
     * @param performer - исполнитель
     */
    public void deletePerformer(Performer performer) {
        performerRepository.delete(performer);
    }

}
