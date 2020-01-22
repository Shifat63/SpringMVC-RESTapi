package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Component
public class IngerdientToIngredientDTO {
    public IngredientDTO convert(Ingredient ingredient)
    {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredientId(ingredient.getIngredientId());
        ingredientDTO.setName(ingredient.getName());
        return ingredientDTO;
    }

    public Set<IngredientDTO> convert(Set<Ingredient> Ingredients)
    {
        Set<IngredientDTO> IngredientDTOs = new HashSet<>();

        for (Ingredient Ingredient : Ingredients) {
            IngredientDTO IngredientDTO = new IngredientDTO();
            IngredientDTO.setIngredientId(Ingredient.getIngredientId());
            IngredientDTO.setName(Ingredient.getName());
            IngredientDTOs.add(IngredientDTO);
        }

        return IngredientDTOs;
    }
}
