package ru.sberbank.jd.web_app.service;

import org.springframework.stereotype.Service;
import ru.sberbank.jd.web_app.entity.ServiceDTO;
import ru.sberbank.jd.web_app.repository.ServiceRepository;

import java.util.List;
import java.util.UUID;

// TODO нужно отрефакторить название сервиса
@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> findAllServices() {
        return serviceRepository.findAll();
    }

    public void saveService(ServiceDTO service) {
        service.setId(UUID.randomUUID().toString());
        serviceRepository.save(service);
    }

    public void updateService(ServiceDTO service) {
        serviceRepository.save(service);
    }

    public ServiceDTO getServiceById(String id) {
        return serviceRepository.findById(id);
    }

    public void deleteServiceById(String id) {
        serviceRepository.delete(id);
    }

}
