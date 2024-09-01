package com.brownpizza.controller;

import com.brownpizza.DTO.IngredientDTO;
import com.brownpizza.DTO.PizzaDTO;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import com.brownpizza.util.DTOMapper;
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
    private final DTOMapper dtoMapper;

    @Autowired
    public PizzaDesignController(PizzaService pizzaService, DTOMapper dtoMapper) {
        this.pizzaService = pizzaService;
        this.dtoMapper = dtoMapper;
    }

    /**
     * Displays the pizza design form to the user.
     * This method fetches the available ingredients and sends them to the thymeleaf template.
     *
     * @param model Thymeleaf Model to pass the data to the view.
     * @return The name of the Thymeleaf template for pizza design.
     */
    @GetMapping
    public String showDesignForm(Model model) {
        List<IngredientDTO> ingredients = pizzaService.getAvailableIngredients();

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("pizza", new PizzaDTO());
        model.addAttribute("types", IngredientDTO.Type.values());
        return "brown-pizza-design";
    }

    @PostMapping
    public String processDesign(
        @Valid @ModelAttribute("pizza") PizzaDTO pizzaDTO, Model model
    ) {
        PizzaDTO createdPizzaDTO = pizzaService.createPizza(pizzaDTO);
        if (createdPizzaDTO == null) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Pizza creation failed");
        }

        model.addAttribute("pizza", createdPizzaDTO);
        Pizza createdPizza = dtoMapper.convertDtoToEntity(createdPizzaDTO, Pizza.class);
        return "redirect:/design/summary" + createdPizza.getId();
    }

    @GetMapping("/summary/{id}")
    public String showSummary(@PathVariable final Long id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pizza not found"));

        PizzaDTO pizzaDTO = dtoMapper.convertEntityToDto(pizza, PizzaDTO.class);
        model.addAttribute("pizza", pizzaDTO);
        return "summary";
    }

    @PutMapping("/update/{id}")
    public String updatePizza(
        @PathVariable final Long id, @Valid @ModelAttribute("pizza") PizzaDTO pizzaDTO, Model model
    ) {
        if (pizzaService.getPizzaById(id).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pizza not found");
        }

        PizzaDTO updatedPizzaDTO = pizzaService.updatePizza(id, pizzaDTO);
        model.addAttribute("pizza", updatedPizzaDTO);

        Pizza updatedPizza = dtoMapper.convertDtoToEntity(updatedPizzaDTO, Pizza.class);
        return "redirect:/design/summary" + updatedPizza.getId();
    }

    @GetMapping("/availableIngredient")
    public ResponseEntity<List<IngredientDTO>> getAvailableIngredient() {
        List<IngredientDTO> ingredientDTOList = pizzaService.getAvailableIngredients();

        if (ingredientDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientDTOList);
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









