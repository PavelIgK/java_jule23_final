package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.ProvidedService;
import ru.sberbank.jd.schedule.entity.ServiceUser;
import ru.sberbank.jd.schedule.repository.ProvidedServiceRepository;
import ru.sberbank.jd.schedule.repository.ServiceUserRepository;

/**
 * Сервис для работы с услугами.
 *
 */
@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ProvidedServiceRepository providedServiceRepository;
    private final ServiceUserRepository serviceUserRepository;

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
     * Получить список услуг по id исполнителя.
     *
     * @param performerId - id исполнителя
     * @return - список услуг
     */
    public List<ProvidedService> getServicesByPerformerId(UUID performerId) {
        return serviceUserRepository
                .findAllByPerformerId(performerId)
                .stream()
                .map(ServiceUser::getProvidedService)
                .collect(Collectors.toList());
    }

    /**
     * Получить список услуг по логину исполнителя.
     *
     * @param performerLogin - логин исполнителя
     * @return - список услуг
     */
    public List<ProvidedService> getServicesByPerformerLogin(String performerLogin) {
        return serviceUserRepository
                .findAllByPerformerLogin(performerLogin)
                .stream()
                .map(ServiceUser::getProvidedService)
                .collect(Collectors.toList());
    }

    //    /**
    //     * Получить список исполнителей по услуге.
    //     *
    //     * @param serviceId - id услуги
    //     * @return - список id исполнителей
    //     */
    //    public List<UUID> getPerformersByServiceId(UUID serviceId) {
    //        serviceUserRepository.
    //    }

}