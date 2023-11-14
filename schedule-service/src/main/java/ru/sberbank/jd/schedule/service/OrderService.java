package ru.sberbank.jd.schedule.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.schedule.OrderDto;
import ru.sberbank.jd.schedule.entity.Order;
import ru.sberbank.jd.schedule.repository.OrderRepository;

/**
 * Сервис для работы с заказами.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(Order::toDto).toList();
    }

    public OrderDto getOrderById(UUID id) {
        return orderRepository.findById(id).get().toDto();
    }

    /**
     * Получение списка заказов по клиенту.
     *
     * @param clientId   идентификатор клиента
     * @param onlyActive только активные
     * @return список заказов
     */
    public List<Order> getOrdersByClient(UUID clientId, Boolean onlyActive) {
        if (onlyActive) {
            return orderRepository.getOrderByClient_IdAndStartDateTimeAfter(clientId, new Date());
        } else {
            return orderRepository.getOrderByClient_Id(clientId);
        }
    }

    /**
     * Получение списка заказов по исполнителю.
     *
     * @param performerId идентификатор исполнителя
     * @param onlyActive  только активные
     * @return список заказов
     */
    public List<Order> getOrdersByPerformer(UUID performerId, Boolean onlyActive) {
        if (onlyActive) {
            return orderRepository.getOrderByPerformer_IdAndStartDateTimeAfter(performerId, new Date());
        } else {
            return orderRepository.getOrderByPerformer_Id(performerId);
        }
    }

    public OrderDto addOrder(OrderDto orderDto) {
        Order order = Order.of(orderDto);
        order.setId(UUID.randomUUID());
        return orderRepository.save(order).toDto();
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        return orderRepository.save(Order.of(orderDto)).toDto();
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
