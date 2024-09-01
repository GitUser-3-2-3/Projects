package com.brownpizza.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientDTO {

    private String id;

    private String name;

    private Type type;

    private BigDecimal price;

    public enum Type {
        MEAT, VEGETABLE, CHEESE, SAUCE, PEPPER
    }
}
