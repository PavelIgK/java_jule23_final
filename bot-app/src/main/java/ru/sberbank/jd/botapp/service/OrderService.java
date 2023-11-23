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

    public List<OrderDto> findOrdersByClient(String id, Boolean isActive) {
        return Arrays.asList(restTemplate.getForObject(
                String.format(getUri()+"/client?id=%s&isActive=%s",id, isActive),
                OrderDto[].class));
    }

    public void addOrder(OrderDto orderDto) {
        restTemplate.postForEntity(getUri(), orderDto, OrderDto.class);
    }

    public void deleteOrderById(String id) {
        restTemplate.delete(getUri()+"/"+id);
    }

}
