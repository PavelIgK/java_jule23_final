package ru.sberbank.jd.schedule.service;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.schedule.entity.ProvidedService;
import ru.sberbank.jd.schedule.repository.ProvidedServiceRepository;

/**
 * Сервис для работы с услугами.
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
    public List<ProvidedServiceDto> getAllServices() {
        return providedServiceRepository.findAll().stream().map(ProvidedService::toDto).toList();
    }

    /**
     * Получить данные услуги по ее id.
     *
     * @param id - id услуги
     * @return - услуга
     */
    public ProvidedServiceDto getServicesById(UUID id) {
        return providedServiceRepository.findById(id).get().toDto();
    }

    /**
     * Добавить новую услугу.
     *
     * @param providedServiceDto - услуга
     * @return - услуга
     */
    public ProvidedServiceDto addService(ProvidedServiceDto providedServiceDto) {
        ProvidedService providedService = ProvidedService.of(providedServiceDto);
        providedService.setId(UUID.randomUUID());
        return providedServiceRepository.save(providedService).toDto();
    }

    /**
     * Обновить услугу.
     *
     * @param providedServiceDto - услуга
     * @return - услуга
     */
    public ProvidedServiceDto updateService(ProvidedServiceDto providedServiceDto) {
        return providedServiceRepository.save(ProvidedService.of(providedServiceDto)).toDto();
    }

    /**
     * Удалить услугу.
     *
     * @param providedService - услуга
     */
    public void deleteService(ProvidedService providedService) {
        providedServiceRepository.delete(providedService);
    }

    /**
     * Удалить услугу по ID.
     *
     * @param id - UUID услуги
     */
    public void deleteServiceById(UUID id) {
        providedServiceRepository.deleteById(id);
    }

}