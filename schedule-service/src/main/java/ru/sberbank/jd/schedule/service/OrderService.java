package ru.sberbank.jd.schedule.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.schedule.entity.Order;
import ru.sberbank.jd.schedule.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrdersByClient(UUID clientId, Boolean onlyActive) {
        if (onlyActive) {
            return orderRepository.getOrderByClient_IdAndStartDateTimeAfter(clientId, new Date());
        } else {
            return orderRepository.getOrderByClient_Id(clientId);
        }
    }

    public List<Order> getOrdersByPerformer(UUID performerId, Boolean onlyActive) {
        if (onlyActive) {
            return orderRepository.getOrderByPerformer_IdAndStartDateTimeAfter(performerId, new Date());
        } else {
            return orderRepository.getOrderByPerformer_Id(performerId);
        }
    }

    public Order addOrder(Order order) {
        order.setId(UUID.randomUUID());
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(getOrderById(order.getId()));
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
