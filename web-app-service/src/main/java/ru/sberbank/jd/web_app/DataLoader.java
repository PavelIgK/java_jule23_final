package ru.sberbank.jd.web_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.web_app.entity.Performer;
import ru.sberbank.jd.web_app.repository.PerformerRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final PerformerRepository performerRepository;

    @Autowired
    public DataLoader(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Performer performer1 = new Performer("performer1",
                "Dmitry", "Kartashov", true, "@docgewq",
                "8-907-003-23-22");
        Performer performer2 = new Performer("performer2",
                "Vadim", "Ratatat", false, "@vadimka333",
                "8-999-003-23-22");
        Performer performer3 = new Performer("performer3",
                "Dmitry", "Kartashov", true, "@docgewq",
                "8-907-003-23-22");
        Performer performer4 = new Performer("performer4",
                "Vadim", "Ratatat", false, "@vadimka333",
                "8-999-003-23-22");
        Performer performer5 = new Performer("performer5",
                "Dmitry", "Kartashov", true, "@docgewq",
                "8-907-003-23-22");
        Performer performer6 = new Performer("performer6",
                "Vadim", "Ratatat", false, "@vadimka333",
                "8-999-003-23-22");
        Performer performer7 = new Performer("performer7",
                "Dmitry", "Kartashov", true, "@docgewq",
                "8-907-003-23-22");
        Performer performer8 = new Performer("performer8",
                "Vadim", "Ratatat", false, "@vadimka333",
                "8-999-003-23-22");

        performerRepository.storage.put(performer1.getId(), performer1);
        performerRepository.storage.put(performer2.getId(), performer2);
        performerRepository.storage.put(performer3.getId(), performer3);
        performerRepository.storage.put(performer4.getId(), performer4);
        performerRepository.storage.put(performer5.getId(), performer5);
        performerRepository.storage.put(performer6.getId(), performer6);
        performerRepository.storage.put(performer7.getId(), performer7);
        performerRepository.storage.put(performer8.getId(), performer8);
    }

}
