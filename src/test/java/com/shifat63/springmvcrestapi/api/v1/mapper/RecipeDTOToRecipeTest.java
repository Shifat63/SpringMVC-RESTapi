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

class RecipeDTOToRecipeTest {

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
    CategoryDTO categoryDTO = new CategoryDTO();
    CategoryDTO categoryDTO2 = new CategoryDTO();
    Set<CategoryDTO> categoryDTOs = new HashSet<>();
    Set<CategoryDTO> categoryDTOs2 = new HashSet<>();
    IngredientDTO ingredientDTO = new IngredientDTO();
    IngredientDTO ingredientDTO2 = new IngredientDTO();
    Set<IngredientDTO> ingredientDTOs = new HashSet<>();
    Set<IngredientDTO> ingredientDTOs2 = new HashSet<>();
    RecipeDTO recipeDTO = new RecipeDTO();
    RecipeDTO recipeDTO2 = new RecipeDTO();
    Set<RecipeDTO> recipeDTOs = new HashSet<>();
    CategoryDTOToCategory categoryDTOToCategory = new CategoryDTOToCategory();
    IngredientDTOToIngredient ingredientDTOToIngredient = new IngredientDTOToIngredient();
    RecipeDTOToRecipe recipeDTOToRecipe = new RecipeDTOToRecipe(categoryDTOToCategory, ingredientDTOToIngredient);

    @BeforeEach
    void setUp() {
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(categoryName);
        categoryDTOs.add(categoryDTO);
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(ingredientName);
        ingredientDTOs.add(ingredientDTO);
        recipeDTO.setRecipeId(recipeId);
        recipeDTO.setName(recipeName);
        recipeDTO.setCategories(categoryDTOs);
        recipeDTO.setIngredients(ingredientDTOs);

        categoryDTO2.setCategoryId(categoryId2);
        categoryDTO2.setName(categoryName2);
        categoryDTOs2.add(categoryDTO2);
        ingredientDTO2.setIngredientId(ingredientId2);
        ingredientDTO2.setName(ingredientName2);
        ingredientDTOs2.add(ingredientDTO2);
        recipeDTO2.setRecipeId(recipeId2);
        recipeDTO2.setName(recipeName2);
        recipeDTO2.setCategories(categoryDTOs2);
        recipeDTO2.setIngredients(ingredientDTOs2);

        recipeDTOs.add(recipeDTO);
        recipeDTOs.add(recipeDTO2);
    }

    @Test
    void convertTest() {
        Recipe recipe = recipeDTOToRecipe.convert(recipeDTO);
        assertEquals(recipeId, recipe.getRecipeId());
        assertEquals(recipeName, recipe.getName());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(1, recipe.getIngredients().size());
        List<Category> categories = new ArrayList<Category>(recipe.getCategories());
        assertEquals(categoryId, categories.get(0).getCategoryId());
        assertEquals(categoryName, categories.get(0).getName());
        List<Ingredient> ingredients = new ArrayList<Ingredient>(recipe.getIngredients());
        assertEquals(ingredientId, ingredients.get(0).getIngredientId());
        assertEquals(ingredientName, ingredients.get(0).getName());
    }

    @Test
    void convertSetTest() {
        Set<Recipe> recipeSet = recipeDTOToRecipe.convertSet(recipeDTOs);
        assertEquals(2, recipeSet.size());
        List<Recipe> recipeList = new ArrayList<Recipe>(recipeSet);

        assertEquals(recipeId, recipeList.get(0).getRecipeId());
        assertEquals(recipeName, recipeList.get(0).getName());
        assertEquals(1, recipeList.get(0).getCategories().size());
        assertEquals(1, recipeList.get(0).getIngredients().size());
        List<Category> categories = new ArrayList<Category>(recipeList.get(0).getCategories());
        assertEquals(categoryId, categories.get(0).getCategoryId());
        assertEquals(categoryName, categories.get(0).getName());
        List<Ingredient> ingredients = new ArrayList<Ingredient>(recipeList.get(0).getIngredients());
        assertEquals(ingredientId, ingredients.get(0).getIngredientId());
        assertEquals(ingredientName, ingredients.get(0).getName());

        assertEquals(recipeId2, recipeList.get(1).getRecipeId());
        assertEquals(recipeName2, recipeList.get(1).getName());
        assertEquals(1, recipeList.get(1).getCategories().size());
        assertEquals(1, recipeList.get(1).getIngredients().size());
        categories = new ArrayList<Category>(recipeList.get(1).getCategories());
        assertEquals(categoryId2, categories.get(0).getCategoryId());
        assertEquals(categoryName2, categories.get(0).getName());
        ingredients = new ArrayList<Ingredient>(recipeList.get(1).getIngredients());
        assertEquals(ingredientId2, ingredients.get(0).getIngredientId());
        assertEquals(ingredientName2, ingredients.get(0).getName());
    }
}