package com.brownpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @NotBlank(message = "Street cannot be blank")
    private String deliveryStreet;

    @NotBlank(message = "City cannot be blank")
    private String deliveryCity;

    @NotBlank(message = "State cannot be blank")
    private String deliveryState;

    @NotBlank(message = "Pin-code cannot be blank")
    @Size(min = 6, max = 6, message = "Pin-code must be 6 char in length")
    private String pinCode;

    private LocalDateTime placedAt;

    @NotNull(message = "Total pizza price cannot be null")
    private BigDecimal subTotal;

    private BigDecimal platformFee;
    private BigDecimal deliveryFee;
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
        pizza.setOrder(this);
    }
}










