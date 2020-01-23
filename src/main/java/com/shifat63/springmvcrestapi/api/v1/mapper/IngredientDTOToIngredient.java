package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Component
public class IngredientDTOToIngredient {
    public Ingredient convert(IngredientDTO ingredientDTO)
    {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientDTO.getIngredientId());
        ingredient.setName(ingredientDTO.getName());
        return ingredient;
    }

    public Set<Ingredient> convertSet(Set<IngredientDTO> ingredientDTOs)
    {
        Set<Ingredient> ingredients = new HashSet<>();

        for (IngredientDTO ingredientDTO : ingredientDTOs) {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientId(ingredientDTO.getIngredientId());
            ingredient.setName(ingredientDTO.getName());
            ingredients.add(ingredient);
        }

        return ingredients;
    }
}
