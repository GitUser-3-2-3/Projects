package com.brownpizza.DTO;

import com.brownpizza.model.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PizzaDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PizzaSize size;

    private CrustType crustType;

    private BigDecimal basePrice;
    private BigDecimal finalPrice;

    private Order order;

    private List<IngredientDTO> ingredients = new ArrayList<>();

    public enum PizzaSize {
        SMALL, MEDIUM, LARGE
    }

    public enum CrustType {
        CHEESEBURST, DOUBLE_CHEESEBURST, FILLED_MOZARELLA,
        HAND_TOSSED, PAN, THIN_CRUST
    }
}
