package com.shifat63.springmvcrestapi.repositories;

import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import com.shifat63.springmvcrestapi.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

// Author: Shifat63

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RecipeRepositoryIT {
    @Autowired
    RecipeRepository recipeRepository;

    Long recipeId = 1L;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
    }

    @Test
    void findAllTest(){
        int counter = 0;
        for (Object i : recipeRepository.findAll()) {
            counter++;
        }
        assertEquals(2, counter);
    }

    @Test
    void findByIdTest(){
        assertEquals(recipeId, recipeRepository.findById(recipeId).get().getRecipeId());
        assertEquals(Optional.empty() ,recipeRepository.findById(565L));
    }

    @Test
    void saveTest(){
        recipe = recipeRepository.findById(recipeId).get();
        Recipe savedRecipe = recipeRepository.save(recipe);
        assertEquals(recipe.getRecipeId(), savedRecipe.getRecipeId()); //While editing id remains same

        Category category = new Category();
        category.setName("Category 1");
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Ingredient 1");
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        recipe = new Recipe();
        recipe.setName("Recipe 1");
        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);
        savedRecipe = recipeRepository.save(recipe);
        assertEquals(3, savedRecipe.getRecipeId()); //While new insertion id should be the next number
    }

    @Test
    void deleteByIdTest(){
        assertEquals(recipeId, recipeRepository.findById(recipeId).get().getRecipeId());
        recipeRepository.deleteById(recipeId);
        assertEquals(Optional.empty() ,recipeRepository.findById(recipeId));
    }
}