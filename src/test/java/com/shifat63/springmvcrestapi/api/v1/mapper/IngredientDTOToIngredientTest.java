package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

// Author: Shifat63

class IngredientDTOToIngredientTest {
    private static final Long ingredientId = 1L;
    private String name = "ingredient 1";
    private static final Long ingredientId2 = 2L;
    private String name2 = "ingredient 2";
    IngredientDTO ingredientDTO = new IngredientDTO();
    IngredientDTO ingredientDTO2 = new IngredientDTO();
    Set<IngredientDTO> IngredientDTOSet = new HashSet<>();
    IngredientDTOToIngredient ingredientDTOToIngredient = new IngredientDTOToIngredient();

    @BeforeEach
    void setUp() {
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(name);

        ingredientDTO2.setIngredientId(ingredientId2);
        ingredientDTO2.setName(name2);

        IngredientDTOSet.add(ingredientDTO);
        IngredientDTOSet.add(ingredientDTO2);
    }

    @Test
    void convertTest() {
        Ingredient ingredient = ingredientDTOToIngredient.convert(ingredientDTO);
        assertEquals(ingredientId, ingredient.getIngredientId());
        assertEquals(name, ingredient.getName());
    }

    @Test
    void convertSetTest() {
        Set<Ingredient> ingredientSet = ingredientDTOToIngredient.convertSet(IngredientDTOSet);
        assertEquals(2, ingredientSet.size());

        List<Ingredient> ingredientList = new ArrayList<Ingredient>(ingredientSet);
        assertEquals(ingredientId, ingredientList.get(0).getIngredientId());
        assertEquals(name, ingredientList.get(0).getName());
        assertEquals(ingredientId2, ingredientList.get(1).getIngredientId());
        assertEquals(name2, ingredientList.get(1).getName());
    }
}