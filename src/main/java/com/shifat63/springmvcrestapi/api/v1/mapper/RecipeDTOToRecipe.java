package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.RecipeDTO;
import com.shifat63.springmvcrestapi.domain.Recipe;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Component
public class RecipeDTOToRecipe {
    private CategoryDTOToCategory categoryDTOToCategory;
    private IngredientDTOToIngredient ingredientDTOToIngredient;

    public RecipeDTOToRecipe(CategoryDTOToCategory categoryDTOToCategory, IngredientDTOToIngredient ingredientDTOToIngredient) {
        this.categoryDTOToCategory = categoryDTOToCategory;
        this.ingredientDTOToIngredient = ingredientDTOToIngredient;
    }

    public Recipe convert(RecipeDTO recipeDTO)
    {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeDTO.getRecipeId());
        recipe.setName(recipeDTO.getName());
        for (CategoryDTO categoryDTO: recipeDTO.getCategories()) {
            recipe.getCategories().add(categoryDTOToCategory.convert(categoryDTO));
        }
        for (IngredientDTO ingredientDTO: recipeDTO.getIngredients()) {
            recipe.getIngredients().add(ingredientDTOToIngredient.convert(ingredientDTO));
        }

        return recipe;
    }

    public Set<Recipe> convertSet(Set<RecipeDTO> recipeDTOs)
    {
        Set<Recipe> recipes = new HashSet<>();

        for (RecipeDTO recipeDTO : recipeDTOs) {
            Recipe recipe = new Recipe();
            recipe.setRecipeId(recipeDTO.getRecipeId());
            recipe.setName(recipeDTO.getName());
            for (CategoryDTO categoryDTO: recipeDTO.getCategories()) {
                recipe.getCategories().add(categoryDTOToCategory.convert(categoryDTO));
            }
            for (IngredientDTO ingredientDTO: recipeDTO.getIngredients()) {
                recipe.getIngredients().add(ingredientDTOToIngredient.convert(ingredientDTO));
            }
            recipes.add(recipe);
        }

        return recipes;
    }
}
