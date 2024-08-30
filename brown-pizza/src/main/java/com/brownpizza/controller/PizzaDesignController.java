package com.brownpizza.controller;

import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
