package com.brownpizza.service;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.repository.IngredientRepository;
import com.brownpizza.repository.PizzaRepository;
import com.brownpizza.util.PriceCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {

    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;

    public BigDecimal calculatePizzaPrice(Pizza pizza) {
        BigDecimal basePrice = PriceCalculator.calculateBasePizzaPrice(
            pizza.getSize(), pizza.getCrustType()
        );
        return PriceCalculator.calculateFinalPizzaPrice(basePrice, pizza.getIngredients());
    }

    public List<Ingredient> getAvailableIngredients() {
        return ingredientRepository.findAll();
    }

    public Pizza savePizza(Pizza pizza) {
        pizza.setBasePrice(
            PriceCalculator.calculateBasePizzaPrice(pizza.getSize(), pizza.getCrustType())
        );
        pizza.setFinalPrice(calculatePizzaPrice(pizza));
        return pizzaRepository.save(pizza);
    }
}
