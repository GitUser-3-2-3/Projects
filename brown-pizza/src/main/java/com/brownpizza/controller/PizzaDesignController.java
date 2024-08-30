package com.brownpizza.controller;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/api/design")
public class PizzaDesignController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaDesignController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@Valid @RequestBody Pizza pizza) {
        Pizza createdPizza = pizzaService.createPizza(pizza);

        if (createdPizza != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdPizza);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        return pizzaService.getPizzaById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pizza not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(
        @PathVariable Long id, @Valid @RequestBody Pizza pizza
    ) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }

        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        return ResponseEntity.ok(updatedPizza);
    }

    @GetMapping("/availableIngredient")
    public ResponseEntity<List<Ingredient>> getAvailableIngredient() {
        List<Ingredient> ingredients = pizzaService.getAvailableIngredients();

        if (ingredients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }
}
