package com.brownpizza.service;

import com.brownpizza.model.Order;
import com.brownpizza.model.Pizza;
import com.brownpizza.repository.OrderRepository;
import com.brownpizza.repository.PizzaRepository;
import com.brownpizza.util.PriceCalculator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private static final BigDecimal DELIVERY_FEE = new BigDecimal("3.99");

    private final OrderRepository orderRepository;
    private final PriceCalculator priceCalculator;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public OrderService(
        OrderRepository orderRepository, PriceCalculator priceCalculator,
        PizzaRepository pizzaRepository
    ) {
        this.orderRepository = orderRepository;
        this.priceCalculator = priceCalculator;
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        order.setPlacedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    private void populateOrderWithPizzas(Order order) {
        List<Pizza> pizzas = order.getPizzas().stream()
            .map(pizza -> pizzaRepository
                .findById(pizza.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Pizza not found with id: " + pizza.getId())
                )).toList();

        order.setPizzas(pizzas);
    }

    private void calculateOrderPrices(Order order) {
        BigDecimal subTotal = priceCalculator
            .calculateOrderSubTotal(order.getPizzas());
        BigDecimal platformFee = priceCalculator
            .calculatePlatformFee(subTotal);
        BigDecimal totalPrice = priceCalculator
            .calculateFinalPrice(subTotal, platformFee, DELIVERY_FEE);

        order.setSubTotal(subTotal);
        order.setPlatformFee(platformFee);
        order.setDeliveryFee(DELIVERY_FEE);
        order.setTotalPrice(totalPrice);
    }

    @Transactional(readOnly = true)
    public Order getOrderById(@NotNull final Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(@NotNull final Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    public Order addPizzaToOrder(@NotNull final Long orderId, @NotNull Pizza pizza) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        order.addPizza(pizza);
        calculateOrderPrices(order);
        return orderRepository.save(order);
    }
}










