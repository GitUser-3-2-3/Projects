package com.brownpizza.controller;

import com.brownpizza.model.Order;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderFormController {

    private final OrderService orderService;

    @Autowired
    public OrderFormController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showOrderForm() {
        return "order-form";
    }

    // Maybe adding pizza model attribute as a parameter might work.
    @PostMapping("/create")
    public String createOrder(@Valid @ModelAttribute Order order, Model model) {
        Order createdOrder = orderService.createOrder(order);
        model.addAttribute("order", createdOrder);
        return "order-summary";
    }

    @PostMapping("/addPizza/{orderId}")
    public String addPizzaToOrder(
        @PathVariable Long orderId, @Valid @ModelAttribute("pizza")Pizza pizza, Model model
    ) {
        Order updatedOrder = orderService.addPizzaToOrder(orderId, pizza);
        model.addAttribute("order", updatedOrder);
        return "order-summary";
    }
}
