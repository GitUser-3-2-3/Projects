package com.brownpizza.util;

import com.brownpizza.DTO.IngredientDTO;
import com.brownpizza.DTO.PizzaDTO;
import com.brownpizza.model.Ingredient;
import com.brownpizza.model.Pizza;
import org.modelmapper.ModelMapper;

import java.util.List;

public class DTOMapper {
    private final ModelMapper modelMapper;

    public DTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<IngredientDTO> convertIngredientListToDtoList(
        List<Ingredient> ingredients
    ) {
        return ingredients.stream()
            .map(ingredient -> modelMapper.map(ingredient, IngredientDTO.class))
            .toList();
    }

    public PizzaDTO convertPizzaToPizzaDto(Pizza pizza) {
        return modelMapper.map(pizza, PizzaDTO.class);
    }
}
