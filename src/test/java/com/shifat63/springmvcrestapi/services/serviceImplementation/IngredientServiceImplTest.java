package com.shifat63.springmvcrestapi.services.serviceImplementation;

import com.shifat63.springmvcrestapi.domain.Ingredient;
import com.shifat63.springmvcrestapi.repositories.IngredientRepository;
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
class IngredientServiceImplTest {
    @Mock
    IngredientRepository ingredientRepository;

    @InjectMocks
    IngredientServiceImpl ingredientService;

    Ingredient ingredient = new Ingredient();
    Set<Ingredient> ingredients = new HashSet<>();

    private static final Long ingredientId = 1L;
    private String name = "ingredient 1";
    private static final Long ingredientId2 = 2L;
    private String name2 = "ingredient 2";

    @BeforeEach
    void setUp() {
        ingredient.setIngredientId(ingredientId);
        ingredient.setName(name);
        ingredients.add(ingredient);
    }

    @Test
    void findAllTest() throws Exception {
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setIngredientId(ingredientId2);
        ingredient2.setName(name2);
        ingredients.add(ingredient2);
        when(ingredientRepository.findAll()).thenReturn(ingredients);
        assertNotNull(ingredientService.findAll());
        assertEquals(2, ingredientService.findAll().size());
        verify(ingredientRepository, times(2)).findAll();
    }

    @Test
    void findByIdTest() throws Exception {
        when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingredient));
        assertNotNull(ingredientService.findById(ingredientId));
        assertEquals(ingredientId, ingredientService.findById(ingredientId).getIngredientId());
        verify(ingredientRepository, times(2)).findById(ingredientId);
    }

    @Test
    void saveOrUpdateTest() throws Exception {
        when(ingredientRepository.save(any())).thenReturn(ingredient);
        assertNotNull(ingredientService.saveOrUpdate(ingredient));
        assertEquals(ingredientId, ingredientService.saveOrUpdate(ingredient).getIngredientId());
        verify(ingredientRepository, times(2)).save(ingredient);
    }

    @Test
    void deleteByIdTest() {
        ingredientRepository.deleteById(ingredientId);
        verify(ingredientRepository, times(1)).deleteById(ingredientId);
    }
}