package com.brownpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @Enumerated(EnumType.STRING)
    private CrustType crustType;

    private BigDecimal basePrice;
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 3, message = "At-least 3 ingredients are needed")
    private List<Ingredient> ingredients = new ArrayList<>();

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









