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

class IngredientToIngredientDTOTest {
    private static final Long ingredientId = 1L;
    private String name = "ingredient 1";
    private static final Long ingredientId2 = 2L;
    private String name2 = "ingredient 2";
    Ingredient ingredient = new Ingredient();
    Ingredient ingredient2 = new Ingredient();
    Set<Ingredient> IngredientSet = new HashSet<>();
    IngredientToIngredientDTO ingredientToIngredientDTO = new IngredientToIngredientDTO();

    @BeforeEach
    void setUp() {
        ingredient.setIngredientId(ingredientId);
        ingredient.setName(name);

        ingredient2.setIngredientId(ingredientId2);
        ingredient2.setName(name2);

        IngredientSet.add(ingredient);
        IngredientSet.add(ingredient2);
    }

    @Test
    void convertTest() {
        IngredientDTO ingredientDTO = ingredientToIngredientDTO.convert(ingredient);
        assertEquals(ingredientId, ingredientDTO.getIngredientId());
        assertEquals(name, ingredientDTO.getName());
    }

    @Test
    void convertSetTest() {
        Set<IngredientDTO> ingredientDTOSet = ingredientToIngredientDTO.convertSet(IngredientSet);
        assertEquals(2, ingredientDTOSet.size());

        List<IngredientDTO> ingredientDTOList = new ArrayList<IngredientDTO>(ingredientDTOSet);
        assertEquals(ingredientId, ingredientDTOList.get(0).getIngredientId());
        assertEquals(name, ingredientDTOList.get(0).getName());
        assertEquals(ingredientId2, ingredientDTOList.get(1).getIngredientId());
        assertEquals(name2, ingredientDTOList.get(1).getName());
    }
}