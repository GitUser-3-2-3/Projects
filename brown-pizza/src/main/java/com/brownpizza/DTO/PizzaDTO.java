package com.brownpizza.DTO;

import com.brownpizza.model.Order;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PizzaDTO {

    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @Enumerated(EnumType.STRING)
    private CrustType crustType;

    private BigDecimal basePrice;
    private BigDecimal finalPrice;

    private Order order;


    @Size(min = 3, message = "At-least 3 ingredients are needed")
    private List<IngredientDTO> ingredients = new ArrayList<>();

    public enum PizzaSize {
        SMALL, MEDIUM, LARGE
    }

    public enum CrustType {
        CHEESEBURST, DOUBLE_CHEESEBURST, FILLED_MOZARELLA,
        HAND_TOSSED, PAN, THIN_CRUST
    }
}
