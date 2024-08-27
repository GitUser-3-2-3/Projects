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
import java.util.List;

@Getter
@Setter
@Entity
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @NotNull(message = "Date & Time cannot be null")
    private LocalDateTime placedAt = LocalDateTime.now();

    @NotNull(message = "Total pizza price cannot be null")
    private BigDecimal sumTotalPizza;

    //* add a method to make 5% of the order price the platform fee.

    @NotNull(message = "Platform fee cannot be null")
    private BigDecimal platformFee;

    //* add a method to make delivery prices dynamic based on relative distance.

    @NotNull(message = "Delivery fee cannot be null")
    private BigDecimal deliveryFee;

    @NotNull(message = "Total price cannot be null")
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pizza> pizzas;
}










