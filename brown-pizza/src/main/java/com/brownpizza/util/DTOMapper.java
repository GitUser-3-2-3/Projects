package com.brownpizza.util;

import com.brownpizza.DTO.IngredientDTO;
import com.brownpizza.model.Ingredient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    public <U, T> U convertEntityToDto(T clazz, Class<U> dto) {
        return modelMapper.map(clazz, dto);
    }

    public <T, U> T convertDtoToEntity(U dto, Class<T> clazz) {
        return modelMapper.map(dto, clazz);
    }
}










