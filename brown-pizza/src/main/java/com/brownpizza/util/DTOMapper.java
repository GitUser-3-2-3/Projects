package com.brownpizza.util;

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

    public <U, T> U convertEntityToDto(T clazz, Class<U> dto) {
        return modelMapper.map(clazz, dto);
    }

    public <T, U> T convertDtoToEntity(U dto, Class<T> clazz) {
        return modelMapper.map(dto, clazz);
    }
}










