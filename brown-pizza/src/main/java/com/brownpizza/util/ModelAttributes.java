package com.brownpizza.util;

import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Order;
import com.brownpizza.model.Pizza;
import com.brownpizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import static com.brownpizza.model.Pizza.*;

@ControllerAdvice
public class ModelAttributes {
    private final PizzaService pizzaService;

    @Autowired
    public ModelAttributes(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @ModelAttribute(name = "order")
    public Order addOrderToModel(Model model) {
        return new Order();
    }

    @ModelAttribute(name = "pizza")
    public Pizza addPizzaToModel(Model model) {
        model.addAttribute("sizes", PizzaSize.values());
        model.addAttribute("crustTypes", CrustType.values());
        return new Pizza();
    }

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        List<Ingredient> ingredientList = pizzaService.getAvailableIngredients();
        model.addAttribute("ingredientList", ingredientList);
        model.addAttribute("ingredientTypes", Ingredient.Type.values());
    }

}
