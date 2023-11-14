package ru.sberbank.jd.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.OrderDto;
import ru.sberbank.jd.schedule.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable("id") String id, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable("id") String id) {
        return orderService.getOrderById(UUID.fromString(id));
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        orderService.deleteOrderById(UUID.fromString(id));
    }

}
