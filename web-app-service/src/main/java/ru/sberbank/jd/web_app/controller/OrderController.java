package ru.sberbank.jd.web_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.jd.dto.schedule.OrderDto;
import ru.sberbank.jd.web_app.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getAllOrders(Model model) {
        List<OrderDto> orderDtoList = orderService.findAllOrders();
        model.addAttribute("orders", orderDtoList);
        return "orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable(value = "id") String id) {
        orderService.deleteOrderById(id);
        return "redirect:/orders";
    }
}

