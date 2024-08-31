package com.brownpizza.controller;

import com.brownpizza.DTO.IngredientDTO;
import com.brownpizza.DTO.PizzaDTO;
import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import com.brownpizza.util.DTOMapper;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/design")
public class PizzaDesignController {

    private final PizzaService pizzaService;
    private final DTOMapper dtoMapper;

    @Autowired
    public PizzaDesignController(PizzaService pizzaService, ModelMapper mapper) {
        this.pizzaService = pizzaService;
        this.dtoMapper = new DTOMapper(mapper);
    }

    @PostMapping
    public ResponseEntity<PizzaDTO> createPizza(@Valid @RequestBody Pizza pizza) {
        Pizza createdPizza = pizzaService.createPizza(pizza);
        PizzaDTO pizzaDTO = dtoMapper.convertPizzaToPizzaDto(createdPizza);

        if (createdPizza != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(pizzaDTO);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        Pizza pizza = pizzaService.getPizzaById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pizza not found"));

        PizzaDTO pizzaDTO = dtoMapper.convertPizzaToPizzaDto(pizza);
        return ResponseEntity.ok(pizzaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> updatePizza(
        @PathVariable Long id, @Valid @RequestBody Pizza pizza
    ) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }

        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        PizzaDTO pizzaDTO = dtoMapper.convertPizzaToPizzaDto(updatedPizza);
        return ResponseEntity.ok(pizzaDTO);
    }

    @GetMapping("/availableIngredient")
    public ResponseEntity<List<IngredientDTO>> getAvailableIngredient() {
        List<Ingredient> ingredients = pizzaService.getAvailableIngredients();

        if (ingredients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<IngredientDTO> ingredientDTO = dtoMapper.convertIngredientListToDtoList(ingredients);
        return ResponseEntity.ok(ingredientDTO);
    }

    @GetMapping("/{id}/basePrice")
    public ResponseEntity<BigDecimal> getBasePizzaPrice(@PathVariable Long id) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal basePrice = pizzaService.getBasePrice(id);
        return ResponseEntity.ok(basePrice);
    }

    @GetMapping("/{id}/finalPrice")
    public ResponseEntity<BigDecimal> getFinalPizzaPrice(@PathVariable Long id) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal basePrice = pizzaService.getFinalPrice(id);
        return ResponseEntity.ok(basePrice);
    }
}
