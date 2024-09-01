package com.brownpizza.controller;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    public ResponseEntity<Pizza> createPizza(@Valid @RequestBody Pizza pizza) {
        Pizza createdPizza = pizzaService.createPizza(pizza);

        if (createdPizza != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(pizza);
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        Pizza pizza = pizzaService.getPizzaById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pizza not found"));

        return ResponseEntity.ok(pizza);
    }

    public ResponseEntity<Pizza> updatePizza(
        @PathVariable Long id, @Valid @RequestBody Pizza pizza
    ) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }

        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        return ResponseEntity.ok(updatedPizza);
    }

    public ResponseEntity<List<Ingredient>> getAvailableIngredient() {
        List<Ingredient> ingredientDTOList = pizzaService.getAvailableIngredients();

        if (ingredientDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientDTOList);
    }

    public ResponseEntity<BigDecimal> getBasePizzaPrice(@PathVariable Long id) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal basePrice = pizzaService.getBasePrice(id);
        return ResponseEntity.ok(basePrice);
    }

    public ResponseEntity<BigDecimal> getFinalPizzaPrice(@PathVariable Long id) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal basePrice = pizzaService.getFinalPrice(id);
        return ResponseEntity.ok(basePrice);
    }
}
