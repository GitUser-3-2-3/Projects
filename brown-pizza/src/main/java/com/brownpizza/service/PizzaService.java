package com.brownpizza.service;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.repository.IngredientRepository;
import com.brownpizza.repository.PizzaRepository;
import com.brownpizza.util.PriceCalculator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;
    private final PriceCalculator priceCalculator;

    @Autowired
    public PizzaService(
        IngredientRepository ingredientRepository, PizzaRepository pizzaRepository,
        PriceCalculator priceCalculator
    ) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional
    public Pizza createPizza(@Valid @NotNull Pizza pizza) {
        pizza.setCreatedAt(LocalDateTime.now());

        populatePizzaWithIngredients(pizza);
        calculatePizzaPrice(pizza);

        return pizzaRepository.save(pizza);
    }

    private void populatePizzaWithIngredients(Pizza pizza) {
        List<Ingredient> ingredients = pizza.getIngredients().stream()
            .map(ingredient -> ingredientRepository
                .findById(ingredient.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Invalid ingredient id: " + ingredient.getId())
                )).toList();

        pizza.setIngredients(ingredients);
    }

    private void calculatePizzaPrice(Pizza pizza) {
        BigDecimal basePrice = priceCalculator
            .calculateBasePizzaPrice(pizza.getSize(), pizza.getCrustType());
        BigDecimal finalPrice = priceCalculator
            .calculateFinalPizzaPrice(basePrice, pizza.getIngredients());

        pizza.setBasePrice(basePrice);
        pizza.setFinalPrice(finalPrice);
    }

    @Transactional(readOnly = true)
    public Optional<Pizza> getPizzaById(final Long id) {
        return pizzaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public BigDecimal getBasePrice(final Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pizza does not exists with id: " + id);
        }
        return pizzaRepository.findBasePriceById(id);
    }

    @Transactional(readOnly = true)
    public BigDecimal getFinalPrice(final Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pizza does not exists with id: " + id);
        }
        return pizzaRepository.findFinalPriceById(id);
    }

    @Transactional
    public Pizza updatePizza(final Long id, @Valid Pizza updatedPizza) {
        return pizzaRepository.findById(id)
            .map(existingPizza -> {
                existingPizza.setSize(updatedPizza.getSize());
                existingPizza.setCrustType(updatedPizza.getCrustType());

                List<Ingredient> ingredients = new ArrayList<>(updatedPizza.getIngredients());
                existingPizza.setIngredients(ingredients);
                populatePizzaWithIngredients(updatedPizza);

                existingPizza.setUpdatedAt(LocalDateTime.now());
                calculatePizzaPrice(updatedPizza);

                existingPizza.setBasePrice(updatedPizza.getBasePrice());
                existingPizza.setFinalPrice(updatedPizza.getFinalPrice());

                return pizzaRepository.save(existingPizza);
            })
            .orElseThrow(() -> new EntityNotFoundException("Pizza not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Ingredient> getAvailableIngredients() {
        return ingredientRepository.findAll();
    }

    /**
     * Method used to add Ingredients to already existing Pizza fetched from the database.
     *
     * @param pizzaId    The pizza, ingredients need to be added in.
     * @param ingredient The ingredients that are being added.
     * @return The Pizza object with newly populated ingredients.
     */
    @Transactional
    public Pizza addIngredientToPizza(final Long pizzaId, Ingredient ingredient) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
            .orElseThrow(() -> new EntityNotFoundException("Pizza not found with id: " + pizzaId));

        pizza.addIngredient(ingredient);
        calculatePizzaPrice(pizza);
        return pizzaRepository.save(pizza);
    }

    @Transactional(readOnly = true)
    public boolean isPizzaValid(Pizza pizza) {
        return pizza.getIngredients().size() >= 3;
    }
}










