package ru.sberbank.jd.web_app.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.dto.schedule.OrderDto;
import ru.sberbank.jd.web_app.config.WebAppConfig;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WebAppConfig webAppConfig;
    private final RestTemplate restTemplate;

    private String getUri(){
        return "http://" + webAppConfig.getScheduleServiceUrl() + ":" + webAppConfig.getScheduleServicePort() + "/orders";
    }

    public List<OrderDto> findAllOrders() {
        return Arrays.asList(restTemplate.getForObject(getUri(), OrderDto[].class));
    }
}
