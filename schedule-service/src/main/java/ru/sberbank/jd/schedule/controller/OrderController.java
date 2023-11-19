package ru.sberbank.jd.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.OrderDto;
import ru.sberbank.jd.schedule.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Заказы услуг", description = "Взаимодействие с заказами")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Добавить заказ")
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить заказ")
    public OrderDto update(@PathVariable("id") String id, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить заказ по UUID")
    public OrderDto get(@PathVariable("id") String id) {
        return orderService.getOrderById(UUID.fromString(id));
    }

    @GetMapping
    @Operation(summary = "Получить все заказы")
    public List<OrderDto> getAll() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить заказ по UUID")
    public void delete(@PathVariable("id") String id) {
        orderService.deleteOrderById(UUID.fromString(id));
    }

}
