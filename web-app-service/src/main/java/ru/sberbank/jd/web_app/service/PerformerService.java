package ru.sberbank.jd.web_app.service;

import org.springframework.stereotype.Service;
import ru.sberbank.jd.web_app.entity.Performer;
import ru.sberbank.jd.web_app.repository.PerformerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PerformerService {

    private final PerformerRepository performerRepository;

    public PerformerService(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    public List<Performer> findAllPerformers() {
        return performerRepository.findAll();
    }

    public void savePerformer(Performer performer) {
        performer.setId(UUID.randomUUID().toString());
        performerRepository.save(performer);
    }

    public void updatePerformer(Performer performer) {
        performerRepository.save(performer);
    }

    public Performer getPerformerById(String id) {
        return performerRepository.findById(id);
    }

    public void deletePerformerById(String id) {
        performerRepository.delete(id);
    }

}
