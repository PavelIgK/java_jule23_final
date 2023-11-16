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

    public List<ProvidedServiceDto> findAllServices() {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
        return restTemplate.getForObject(uri, List.class);
    }

    public void saveService(ProvidedServiceDto serviceDto) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
        serviceDto.setId(UUID.randomUUID());
        restTemplate.postForEntity(uri, serviceDto, ProvidedServiceDto.class);
    }

    public void updateService(ProvidedServiceDto serviceDto) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
        restTemplate.put(uri + "/" + serviceDto.getId(), serviceDto);
    }

    public ProvidedServiceDto getServiceById(String id) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
        return restTemplate.getForObject(uri + "/" + id, ProvidedServiceDto.class);
    }

    public void deleteServiceById(String id) {
        String uri =
                "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/services";
        restTemplate.delete(uri + "/" + id);
    }

}
