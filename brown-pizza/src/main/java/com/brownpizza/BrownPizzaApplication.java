package com.brownpizza;

import com.brownpizza.model.Ingredient;
import com.brownpizza.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.brownpizza.model.Ingredient.Type;

@SpringBootApplication
public class BrownPizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrownPizzaApplication.class, args);
    }

    public CommandLineRunner dataLoader(IngredientRepository repository) {
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient(
                "PEPP", "Pepperoni", Type.MEAT, new BigDecimal("1.50")
            ),
            new Ingredient(
                "SAUS", "Sausage", Type.MEAT, new BigDecimal("1.75")
            ),
            new Ingredient(
                "BACO", "Bacon", Type.MEAT, new BigDecimal("2.00")
            ),
            new Ingredient(
                "HAMM", "Ham", Type.MEAT, new BigDecimal("1.25")
            ),
            new Ingredient(
                "MUSH", "Mushrooms", Type.VEGETABLE, new BigDecimal("0.75")
            ),
            new Ingredient(
                "ONIO", "Onions", Type.VEGETABLE, new BigDecimal("0.50")
            ),
            new Ingredient(
                "BELL", "Bell Peppers", Type.VEGETABLE, new BigDecimal("0.75")
            ),
            new Ingredient(
                "TOMA", "Tomatoes", Type.VEGETABLE, new BigDecimal("0.50")
            ),
            new Ingredient(
                "OLIV", "Olives", Type.VEGETABLE, new BigDecimal("1.00")
            ),
            new Ingredient(
                "CHED", "Cheddar Cheese", Type.CHEESE, new BigDecimal("1.25")
            ),
            new Ingredient(
                "MOZZ", "Mozzarella Cheese", Type.CHEESE, new BigDecimal("1.50")
            ),
            new Ingredient(
                "FETA", "Feta Cheese", Type.CHEESE, new BigDecimal("1.75")
            ),
            new Ingredient(
                "MAR1", "Marinara Sauce", Type.SAUCE, new BigDecimal("0.50")
            ),
            new Ingredient(
                "RANC", "Ranch Sauce", Type.SAUCE, new BigDecimal("0.75")
            ),
            new Ingredient(
                "JALA", "Jalapeños", Type.PEPPERS, new BigDecimal("0.25")
            )
        );

        return args -> repository.saveAll(ingredients);
    }
}
