package ru.sberbank.jd.web_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.web_app.entity.ServiceDTO;
import ru.sberbank.jd.web_app.repository.ServiceRepository;

import java.math.BigDecimal;

@Component
public class DataLoader implements ApplicationRunner {

    private final ServiceRepository serviceRepository;

    @Autowired
    public DataLoader(ServiceRepository serviceRepository) {

        this.serviceRepository = serviceRepository;
    }


        ServiceDTO service = new ServiceDTO("Стрижка мужская", new BigDecimal("500.00"), 30, "Что-то здесь стрижется");
        ServiceDTO service1 = new ServiceDTO("Стрижка женская", new BigDecimal("1000.00"), 60, "Что-то здесь стрижется");
        ServiceDTO service2 = new ServiceDTO("Покраска",  new BigDecimal("2000.00"), 120, "Что-то здесь красится");

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

        //serviceRepository.serviceStorage.put(service.getId(), service);
        //serviceRepository.serviceStorage.put(service1.getId(), service1);
        //serviceRepository.serviceStorage.put(service2.getId(), service2);
}
