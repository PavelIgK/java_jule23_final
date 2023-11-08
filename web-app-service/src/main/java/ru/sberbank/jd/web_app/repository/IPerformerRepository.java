package ru.sberbank.jd.web_app.repository;

import ru.sberbank.jd.web_app.entity.Performer;

import java.util.List;

public interface IPerformerRepository {
    List<Performer> findAll();

    void save(Performer performer);

    Performer findById(String id);

    void delete(String id);
}
