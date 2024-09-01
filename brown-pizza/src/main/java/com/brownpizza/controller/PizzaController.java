package com.brownpizza.controller;

import com.brownpizza.DTO.IngredientDTO;
import com.brownpizza.DTO.PizzaDTO;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import com.brownpizza.util.DTOMapper;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
    private final DTOMapper dtoMapper;

    public PizzaController(PizzaService pizzaService, ModelMapper mapper) {
        this.pizzaService = pizzaService;
        this.dtoMapper = new DTOMapper(mapper);
    }

    public ResponseEntity<PizzaDTO> createPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO createdPizza = pizzaService.createPizza(pizzaDTO);

        if (createdPizza != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(pizzaDTO);
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        Pizza pizza = pizzaService.getPizzaById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pizza not found"));

        PizzaDTO pizzaDTO = dtoMapper.convertEntityToDto(pizza, PizzaDTO.class);
        return ResponseEntity.ok(pizzaDTO);
    }

    public ResponseEntity<PizzaDTO> updatePizza(
        @PathVariable Long id, @Valid @RequestBody PizzaDTO pizzaDTO
    ) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }

        PizzaDTO updatedPizza = pizzaService.updatePizza(id, pizzaDTO);
        return ResponseEntity.ok(updatedPizza);
    }

    public ResponseEntity<List<IngredientDTO>> getAvailableIngredient() {
        List<IngredientDTO> ingredientDTOList = pizzaService.getAvailableIngredients();

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
