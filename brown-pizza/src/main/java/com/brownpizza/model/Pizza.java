package com.brownpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Date & Time cannot be null")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotBlank(message = "Size cannot be empty")
    private PizzaSize size;

    @NotBlank(message = "Crust type cannot be empty")
    private CrustType crustType;

    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 3, message = "At-least 3 ingredients are needed")
    private List<Ingredient> ingredients;

    @NotNull(message = "Base price cannot be null")
    @Positive(message = "Base price cannot be negative")
    private BigDecimal basePrice;

    @NotNull(message = "Final price cannot be null")
    @Positive(message = "Final price cannot be negative")
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public enum PizzaSize {
        SMALL, MEDIUM, LARGE
    }

    public enum CrustType {
        CHEESEBURST, DOUBLE_CHEESEBURST, FILLED_MOZARELLA,
        HAND_TOSSED, PAN, THIN_CRUST
    }
}









