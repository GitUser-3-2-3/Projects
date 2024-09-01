package com.brownpizza.util;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.model.Pizza.CrustType;
import com.brownpizza.model.Pizza.PizzaSize;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Component
public class PriceCalculator {

    public static final BigDecimal PLATFORM_FEE_PERCENTAGE = new BigDecimal("0.05");

    public BigDecimal calculateBasePizzaPrice(
        @NotNull PizzaSize pizzaSize, @NotNull CrustType crustType
    ) {
        return switch (pizzaSize) {
            case SMALL -> switch (crustType) {
                case THIN_CRUST, HAND_TOSSED, PAN -> new BigDecimal("1.99");
                default -> new BigDecimal("2.99");
            };
            case MEDIUM -> switch (crustType) {
                case THIN_CRUST, HAND_TOSSED, PAN -> new BigDecimal("2.99");
                default -> new BigDecimal("3.99");
            };
            case LARGE -> switch (crustType) {
                case THIN_CRUST, HAND_TOSSED, PAN -> new BigDecimal("3.99");
                default -> new BigDecimal("4.99");
            };
        };
    }

    public BigDecimal calculateFinalPizzaPrice(
        @NotNull BigDecimal basePrice, @NotNull List<Ingredient> ingredients
    ) {
        BigDecimal ingredientCost = ingredients.stream()
            .map(ingredient -> {
                if (ingredient.getPrice() == null) {
                    log.warn("Ingredient has no price {}", ingredient.getName());
                    return BigDecimal.ZERO;
                }
                return ingredient.getPrice();
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return basePrice.add(ingredientCost);
    }

    public BigDecimal calculateOrderSubTotal(@NotNull List<Pizza> pizzas) {
        return pizzas.stream().map(Pizza::getFinalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculatePlatformFee(@NotNull BigDecimal subTotal) {
        return subTotal.multiply(PLATFORM_FEE_PERCENTAGE)
            .setScale(2, RoundingMode.HALF_UP);
    }

    // todo Add a dynamic delivery fee method that charges delivery fee according to distance.

    public BigDecimal calculateFinalPrice(
        @NotNull BigDecimal subtotal, @NotNull BigDecimal platformFee, @NotNull BigDecimal deliveryFee
    ) {
        return subtotal.add(platformFee).add(deliveryFee);
    }
}









