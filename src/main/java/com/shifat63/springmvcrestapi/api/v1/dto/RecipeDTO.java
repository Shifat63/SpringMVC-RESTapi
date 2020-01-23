package com.shifat63.springmvcrestapi.api.v1.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Data
public class RecipeDTO {
    private Long recipeId;
    private String name;
    private Set<CategoryDTO> categories = new HashSet<>();
    private Set<IngredientDTO> ingredients = new HashSet<>();
}
