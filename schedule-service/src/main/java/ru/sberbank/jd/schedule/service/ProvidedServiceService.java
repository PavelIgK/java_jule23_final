package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.ProvidedService;
import ru.sberbank.jd.schedule.repository.ProvidedServiceRepository;

/**
 * Сервис для работы с услугами.
 *
 */
@Service
@RequiredArgsConstructor
public class ProvidedServiceService {

    private final ProvidedServiceRepository providedServiceRepository;

    /**
     * Получить список всех услуг.
     *
     * @return - список услуг
     */
    public List<ProvidedService> getAllServices() {
        return providedServiceRepository.findAll();
    }

    /**
     * Получить данные услуги по ее id.
     *
     * @param id - id услуги
     * @return - услуга
     */
    public ProvidedService getServicesById(UUID id) {
        return providedServiceRepository.findById(id).get();
    }

    /**
     * Добавить новую услугу.
     *
     * @param providedService - услуга
     * @return - услуга
     */
    public ProvidedService addService(ProvidedService providedService) {
        providedService.setId(UUID.randomUUID());
        return providedServiceRepository.save(providedService);
    }

    /**
     * Обновить услугу.
     *
     * @param providedService - услуга
     * @return - услуга
     */
    public ProvidedService updateService(ProvidedService providedService) {
        return providedServiceRepository.save(getServicesById(providedService.getId()));
    }

    /**
     * Удалить услугу.
     *
     * @param providedService - услуга
     */
    public void deleteService(ProvidedService providedService) {
        providedServiceRepository.delete(providedService);
    }

}