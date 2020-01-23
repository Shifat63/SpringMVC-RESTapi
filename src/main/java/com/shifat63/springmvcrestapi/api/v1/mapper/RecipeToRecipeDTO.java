package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.RecipeDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import com.shifat63.springmvcrestapi.domain.Recipe;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Component
public class RecipeToRecipeDTO {

    private CategoryToCategoryDTO categoryToCategoryDTO;
    private IngredientToIngredientDTO ingredientToIngredientDTO;

    public RecipeToRecipeDTO(CategoryToCategoryDTO categoryToCategoryDTO, IngredientToIngredientDTO ingredientToIngredientDTO) {
        this.categoryToCategoryDTO = categoryToCategoryDTO;
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
    }

    public RecipeDTO convert(Recipe recipe)
    {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipe.getRecipeId());
        recipeDTO.setName(recipe.getName());
        for (Category category: recipe.getCategories()) {
            recipeDTO.getCategories().add(categoryToCategoryDTO.convert(category));
        }
        for (Ingredient ingredient: recipe.getIngredients()) {
            recipeDTO.getIngredients().add(ingredientToIngredientDTO.convert(ingredient));
        }

        return recipeDTO;
    }

    public Set<RecipeDTO> convertSet(Set<Recipe> recipes)
    {
        Set<RecipeDTO> recipeDTOs = new HashSet<>();
        for (Recipe recipe : recipes) {
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setRecipeId(recipe.getRecipeId());
            recipeDTO.setName(recipe.getName());
            for (Category category: recipe.getCategories()) {
                recipeDTO.getCategories().add(categoryToCategoryDTO.convert(category));
            }
            for (Ingredient ingredient: recipe.getIngredients()) {
                recipeDTO.getIngredients().add(ingredientToIngredientDTO.convert(ingredient));
            }
            recipeDTOs.add(recipeDTO);
        }

        return recipeDTOs;
    }
}
