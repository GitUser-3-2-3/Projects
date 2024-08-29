package com.brownpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pizza_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be 3 char long")
    private String customerName;

    @Embedded
    private Address deliveryAddress;

    @NotNull(message = "Date & Time cannot be null")
    private LocalDateTime placedAt;

    @NotNull(message = "Total pizza price cannot be null")
    @Positive(message = "Total Pizza price cannot be negative")
    private BigDecimal subTotal;

    @NotNull(message = "Platform fee cannot be null")
    @Positive(message = "Platform fee cannot be negative")
    private BigDecimal platformFee;

    @NotNull(message = "Delivery fee cannot be null")
    @Positive(message = "Deliver fee cannot be negative")
    private BigDecimal deliveryFee;

    @NotNull(message = "Total price cannot be null")
    @Positive(message = "Total price cannot be negative")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pizza> pizzas;

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
        pizza.setOrder(this);
    }
}










