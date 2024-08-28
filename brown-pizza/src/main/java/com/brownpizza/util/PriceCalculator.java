package com.brownpizza.util;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PriceCalculator {

    public static final BigDecimal PLATFORM_FEE_PERCENTAGE = new BigDecimal("0.05");

    public static BigDecimal calculateBasePizzaPrice(@NotBlank String size) {
        if ("Small".equalsIgnoreCase(size)) {
            return new BigDecimal("8.99");
        } else if ("Medium".equalsIgnoreCase(size)) {
            return new BigDecimal("10.99");
        } else {
            return new BigDecimal("12.99");
        }
    }

    public static BigDecimal calculateFinalPizzaPrice(
        @NotNull BigDecimal basePrice, @NotNull List<Ingredient> ingredients
    ) {
        BigDecimal ingredientCost = ingredients.stream()
            .map(Ingredient::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return basePrice.add(ingredientCost);
    }

    public static BigDecimal calculateOrderSubTotal(@NotNull List<Pizza> pizzas) {
        return pizzas.stream().map(Pizza::getFinalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal calculatePlatformFee(@NotNull BigDecimal subTotal) {
        return subTotal.multiply(PLATFORM_FEE_PERCENTAGE)
            .setScale(2, RoundingMode.HALF_UP);
    }

    // todo Add a dynamic delivery fee method that charges delivery fee according to distance.

    public static BigDecimal calculateFinalPrice(
        @NotNull BigDecimal subtotal, @NotNull BigDecimal platformFee, @NotNull BigDecimal deliveryFee
    ) {
        return subtotal.add(platformFee).add(deliveryFee);
    }
}









