package ru.sberbank.jd.web_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.web_app.config.WebAppConfig;

@Service
@RequiredArgsConstructor
public class ProvidedServiceService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
    }

    public List<ProvidedServiceDto> findAllServices() {
        return restTemplate.getForObject(getUri(), List.class);
    }

    public void saveService(ProvidedServiceDto serviceDto) {
        serviceDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(getUri(), serviceDto, ProvidedServiceDto.class);
    }

    public void updateService(ProvidedServiceDto serviceDto) {
        restTemplate.put(getUri() + "/" + serviceDto.getId(), serviceDto);
    }

    public ProvidedServiceDto getServiceById(String id) {
        return restTemplate.getForObject(getUri() + "/" + id, ProvidedServiceDto.class);
    }

    public void deleteServiceById(String id) {
        restTemplate.delete(getUri() + "/" + id);
    }

}
