package com.brownpizza.service;

import com.brownpizza.model.Address;
import com.brownpizza.model.Order;
import com.brownpizza.model.Pizza;
import com.brownpizza.repository.OrderRepository;
import com.brownpizza.util.PriceCalculator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private static final BigDecimal DELIVERY_FEE = new BigDecimal("3.99");

    private final OrderRepository orderRepository;
    private final PriceCalculator priceCalculator;

    @Transactional
    public Order createOrder(@Valid final Order order, final Address address) {
        order.setPlacedAt(LocalDateTime.now());
        order.setDeliveryAddress(address);

        calculateOrderPrices(order);
        return orderRepository.save(order);
    }

    private void calculateOrderPrices(final Order order) {
        BigDecimal subTotal = priceCalculator.calculateOrderSubTotal(order.getPizzas());
        BigDecimal platformFee = priceCalculator.calculatePlatformFee(subTotal);
        BigDecimal totalPrice = priceCalculator.calculateFinalPrice(subTotal, platformFee, DELIVERY_FEE);

        order.setSubTotal(subTotal);
        order.setPlatformFee(platformFee);
        order.setDeliveryFee(DELIVERY_FEE);
        order.setTotalPrice(totalPrice);
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(@NotNull final Long id) {
        return orderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(@NotNull final Long id) {
        orderRepository.deleteById(id);
    }

    public Order addPizzaToOrder(final Long orderId, final Pizza pizza) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        order.addPizza(pizza);
        calculateOrderPrices(order);
        return orderRepository.save(order);
    }
}










