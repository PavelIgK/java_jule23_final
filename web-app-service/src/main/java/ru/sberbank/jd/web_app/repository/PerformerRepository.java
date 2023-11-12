package ru.sberbank.jd.web_app.repository;

import org.springframework.stereotype.Repository;
import ru.sberbank.jd.web_app.entity.Performer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PerformerRepository implements IPerformerRepository {

    public Map<String, Performer> storage = new HashMap<>();

    public List<Performer> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void save(Performer performer) {
        storage.put(performer.getId(), performer);
    }

    @Override
    public Performer findById(String id) {
        return storage.get(id);
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }
}
