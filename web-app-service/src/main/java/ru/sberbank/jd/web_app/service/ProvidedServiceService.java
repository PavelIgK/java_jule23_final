package ru.sberbank.jd.web_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProvidedServiceService {

    private final String uri = "http://schedule:8081/services";
    private final RestTemplate restTemplate;

    public List<ProvidedServiceDto> findAllServices() {
        return restTemplate.getForObject(uri, List.class);
    }

    public void saveService(ProvidedServiceDto serviceDto) {
        serviceDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(uri, serviceDto, ProvidedServiceDto.class);
    }

    public void updateService(ProvidedServiceDto serviceDto) {
        restTemplate.put(uri + "/" + serviceDto.getId(), serviceDto);
    }

    public ProvidedServiceDto getServiceById(String id) {
        return restTemplate.getForObject(uri + "/" + id, ProvidedServiceDto.class);
    }

    public void deleteServiceById(String id) {
        restTemplate.delete(uri + "/" + id);
    }

}
