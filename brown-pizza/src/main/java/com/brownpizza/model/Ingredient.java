package com.brownpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Ingredient {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, message = "Name cannot be less than 5 char")
    private String name;

    @NotNull(message = "Type cannot be null")
    private Type type;

    @Positive(message = "Price cannot be negative")
    @Column(nullable = false)
    private BigDecimal price;

    public enum Type {
        MEAT, VEGETABLE, CHEESE, SAUCE, PEPPER
    }
}









