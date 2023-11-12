package ru.sberbank.jd.web_app.repository;

import org.springframework.stereotype.Repository;
import ru.sberbank.jd.web_app.entity.ServiceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ServiceRepository {

    public Map<String, ServiceDTO> serviceStorage = new HashMap<>();

    public List<ServiceDTO> findAll() {
        return new ArrayList<>(serviceStorage.values());
    }

    public void save(ServiceDTO service) {
        serviceStorage.put(service.getId(), service);
    }

    public ServiceDTO findById(String id) {
        return serviceStorage.get(id);
    }

    public void delete(String id) {
        serviceStorage.remove(id);
    }
}
