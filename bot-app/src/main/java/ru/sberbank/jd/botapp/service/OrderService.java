package ru.sberbank.jd.botapp.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.botapp.config.BotConfig;
import ru.sberbank.jd.dto.schedule.OrderDto;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    private String getUri(){
        return botConfig.getScheduleServiceUrl() + "/orders";
    }

    public List<OrderDto> findAllOrders() {
        return Arrays.asList(restTemplate.getForObject(getUri(), OrderDto[].class));
    }

    public List<OrderDto> findOrdersByClient(String id) {
        return Arrays.asList(restTemplate.getForObject(getUri()+"/"+id, OrderDto[].class));
    }

}
