package com.brownpizza.controller;

import com.brownpizza.model.Order;
import com.brownpizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("order")
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
    public String createOrder(Order order, Model model, SessionStatus status) {
        Order createdOrder = orderService.createOrder(order);
        model.addAttribute("order", createdOrder);

        status.setComplete();
        return "redirect:/";
    }
}
