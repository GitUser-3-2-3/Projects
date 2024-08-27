package com.brownpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();

    @NotBlank(message = "Size cannot be empty")
    private String size;

    @NotBlank(message = "Crust type cannot be empty")
    private String crustType;

    @ManyToMany
    @Size(min = 3, message = "At-least 3 ingredients are needed")
    private List<Ingredient> ingredients;

    @NotNull(message = "Base price cannot be null")
    private BigDecimal basePrice;

    @NotNull(message = "Final price cannot be null")
    private BigDecimal finalPrice;
}









