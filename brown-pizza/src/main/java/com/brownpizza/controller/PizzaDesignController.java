package com.brownpizza.controller;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/design")
public class PizzaDesignController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaDesignController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /**
     * Displays the pizza design form to the user.
     *
     * @return The name of the Thymeleaf template for pizza design.
     */
    @GetMapping
    public String showDesignForm() {
        return "brown-pizza-design";
    }

    @PostMapping
    public String processDesign(
        @Valid @ModelAttribute("pizza") Pizza pizza, Model model
    ) {
        Pizza createdPizza = pizzaService.createPizza(pizza);
        if (createdPizza == null) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Pizza creation failed");
        }

        model.addAttribute("pizza", createdPizza);
        return "redirect:/design/summary/" + createdPizza.getId();
    }

    @GetMapping("/summary/{id}")
    public String showSummary(@PathVariable final Long id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pizza not found"));

        model.addAttribute("pizza", pizza);
        return "summary";
    }

    @PutMapping("/update/{id}")
    public String updatePizza(
        @PathVariable final Long id, @Valid @ModelAttribute("pizza") Pizza pizza, Model model
    ) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }

        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        model.addAttribute("pizza", updatedPizza);

        return "redirect:/design/summary/" + updatedPizza.getId();
    }

    @GetMapping("/availableIngredient")
    public ResponseEntity<List<Ingredient>> getAvailableIngredient() {
        List<Ingredient> ingredientList = pizzaService.getAvailableIngredients();

        if (ingredientList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientList);
    }

    @ResponseBody
    @GetMapping("{id}/basePrice")
    public String getBasePizzaPrice(@PathVariable Long id) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }
        BigDecimal basePrice = pizzaService.getBasePrice(id);
        return basePrice.toString();
    }

    @ResponseBody
    @GetMapping("{id}/finalPrice")
    public String getFinalPizzaPrice(@PathVariable Long id) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }
        BigDecimal finalPrice = pizzaService.getFinalPrice(id);
        return finalPrice.toString();
    }
}









