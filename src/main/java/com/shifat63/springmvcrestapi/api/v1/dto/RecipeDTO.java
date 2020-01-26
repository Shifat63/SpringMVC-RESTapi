package com.shifat63.springmvcrestapi.api.v1.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Data
public class RecipeDTO {
    @ApiModelProperty(value = "Id of a certain recipe")
    private Long recipeId;

    @ApiModelProperty(value = "Name of recipe", notes = "Name must not be empty and must be between 1 to 200 characters", required = true)
    private String name;

    @ApiModelProperty(value = "Categories of recipe", notes = "Recipe must belong to one or more than one category", required = true)
    private Set<CategoryDTO> categories = new HashSet<>();

    @ApiModelProperty(value = "Ingredients of recipe", notes = "Recipe must have one or more ingredients", required = true)
    private Set<IngredientDTO> ingredients = new HashSet<>();
}
