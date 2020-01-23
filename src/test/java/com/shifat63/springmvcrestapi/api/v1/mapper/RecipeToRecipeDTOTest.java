package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.RecipeDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import com.shifat63.springmvcrestapi.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

// Author: Shifat63

class RecipeToRecipeDTOTest {
    private static final Long recipeId = 1L;
    private String recipeName = "recipe 1";
    private static final Long recipeId2 = 2L;
    private String recipeName2 = "recipe 2";
    private static final Long categoryId = 1L;
    private String categoryName = "category 1";
    private static final Long categoryId2 = 2L;
    private String categoryName2 = "category 2";
    private static final Long ingredientId = 1L;
    private String ingredientName = "ingredient 1";
    private static final Long ingredientId2 = 2L;
    private String ingredientName2 = "ingredient 2";
    Category category = new Category();
    Category category2 = new Category();
    Set<Category> categories = new HashSet<>();
    Set<Category> categories2 = new HashSet<>();
    Ingredient ingredient = new Ingredient();
    Ingredient ingredient2 = new Ingredient();
    Set<Ingredient> ingredients = new HashSet<>();
    Set<Ingredient> ingredients2 = new HashSet<>();
    Recipe recipe = new Recipe();
    Recipe recipe2 = new Recipe();
    Set<Recipe> recipes = new HashSet<>();
    CategoryToCategoryDTO categoryToCategoryDTO = new CategoryToCategoryDTO();
    IngredientToIngredientDTO ingredientToIngredientDTO = new IngredientToIngredientDTO();
    RecipeToRecipeDTO recipeToRecipeDTO = new RecipeToRecipeDTO(categoryToCategoryDTO, ingredientToIngredientDTO);

    @BeforeEach
    void setUp() {
        category.setCategoryId(categoryId);
        category.setName(categoryName);
        categories.add(category);
        ingredient.setIngredientId(ingredientId);
        ingredient.setName(ingredientName);
        ingredients.add(ingredient);
        recipe.setRecipeId(recipeId);
        recipe.setName(recipeName);
        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);

        category2.setCategoryId(categoryId2);
        category2.setName(categoryName2);
        categories2.add(category2);
        ingredient2.setIngredientId(ingredientId2);
        ingredient2.setName(ingredientName2);
        ingredients2.add(ingredient2);
        recipe2.setRecipeId(recipeId2);
        recipe2.setName(recipeName2);
        recipe2.setCategories(categories2);
        recipe2.setIngredients(ingredients2);

        recipes.add(recipe);
        recipes.add(recipe2);
    }

    @Test
    void convertTest() {
        RecipeDTO recipeDTO = recipeToRecipeDTO.convert(recipe);
        assertEquals(recipeId, recipeDTO.getRecipeId());
        assertEquals(recipeName, recipeDTO.getName());
        assertEquals(1, recipeDTO.getCategories().size());
        assertEquals(1, recipeDTO.getIngredients().size());
        List<CategoryDTO> categories = new ArrayList<CategoryDTO>(recipeDTO.getCategories());
        assertEquals(categoryId, categories.get(0).getCategoryId());
        assertEquals(categoryName, categories.get(0).getName());
        List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>(recipeDTO.getIngredients());
        assertEquals(ingredientId, ingredients.get(0).getIngredientId());
        assertEquals(ingredientName, ingredients.get(0).getName());
    }

    @Test
    void convertSetTest() {
        Set<RecipeDTO> recipeDTOSet = recipeToRecipeDTO.convertSet(recipes);
        assertEquals(2, recipeDTOSet.size());
        List<RecipeDTO> recipeDTOList = new ArrayList<RecipeDTO>(recipeDTOSet);

        assertEquals(recipeId, recipeDTOList.get(1).getRecipeId());
        assertEquals(recipeName, recipeDTOList.get(1).getName());
        assertEquals(1, recipeDTOList.get(1).getCategories().size());
        assertEquals(1, recipeDTOList.get(1).getIngredients().size());
        List<CategoryDTO> categories = new ArrayList<CategoryDTO>(recipeDTOList.get(1).getCategories());
        assertEquals(categoryId, categories.get(0).getCategoryId());
        assertEquals(categoryName, categories.get(0).getName());
        List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>(recipeDTOList.get(1).getIngredients());
        assertEquals(ingredientId, ingredients.get(0).getIngredientId());
        assertEquals(ingredientName, ingredients.get(0).getName());

        assertEquals(recipeId2, recipeDTOList.get(0).getRecipeId());
        assertEquals(recipeName2, recipeDTOList.get(0).getName());
        assertEquals(1, recipeDTOList.get(0).getCategories().size());
        assertEquals(1, recipeDTOList.get(0).getIngredients().size());
        categories = new ArrayList<CategoryDTO>(recipeDTOList.get(0).getCategories());
        assertEquals(categoryId2, categories.get(0).getCategoryId());
        assertEquals(categoryName2, categories.get(0).getName());
        ingredients = new ArrayList<IngredientDTO>(recipeDTOList.get(0).getIngredients());
        assertEquals(ingredientId2, ingredients.get(0).getIngredientId());
        assertEquals(ingredientName2, ingredients.get(0).getName());
    }
}