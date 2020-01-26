package com.shifat63.springmvcrestapi.services.serviceImplementation;

import com.shifat63.springmvcrestapi.domain.Recipe;
import com.shifat63.springmvcrestapi.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Author: Shifat63

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    Recipe recipe = new Recipe();
    Set<Recipe> recipes = new HashSet<>();

    private static final Long recipeId = 1L;
    private String name = "recipe 1";
    private static final Long recipeId2 = 2L;
    private String name2 = "recipe 2";

    @BeforeEach
    void setUp() {
        recipe.setRecipeId(recipeId);
        recipe.setName(name);
        recipes.add(recipe);
    }

    @Test
    void findAllTest() throws Exception {
        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(recipeId2);
        recipe2.setName(name2);
        recipes.add(recipe2);
        when(recipeRepository.findAll()).thenReturn(recipes);
        assertNotNull(recipeService.findAll());
        assertEquals(2, recipeService.findAll().size());
        verify(recipeRepository, times(2)).findAll();
    }

    @Test
    void findByIdTest() throws Exception {
        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));
        assertNotNull(recipeService.findById(recipeId));
        assertEquals(recipeId, recipeService.findById(recipeId).getRecipeId());
        verify(recipeRepository, times(2)).findById(recipeId);
    }

    @Test
    void saveOrUpdateTest() throws Exception {
        when(recipeRepository.save(any())).thenReturn(recipe);
        assertNotNull(recipeService.saveOrUpdate(recipe));
        assertEquals(recipeId, recipeService.saveOrUpdate(recipe).getRecipeId());
        verify(recipeRepository, times(2)).save(recipe);
    }

    @Test
    void deleteByIdTest() {
        recipeRepository.deleteById(recipeId);
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }
}