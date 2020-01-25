package com.shifat63.springmvcrestapi.repositories;

import com.shifat63.springmvcrestapi.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

// Author: Shifat63

@ExtendWith(SpringExtension.class)
@DataJpaTest
class IngredientRepositoryIT {
    @Autowired
    IngredientRepository ingredientRepository;

    Long ingredientId = 1L;
    Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient();
    }

    @Test
    void findAllTest(){
        int counter = 0;
        for (Object i : ingredientRepository.findAll()) {
            counter++;
        }
        assertEquals(8, counter);
    }

    @Test
    void findByIdTest(){
        assertEquals(ingredientId, ingredientRepository.findById(ingredientId).get().getIngredientId());
        assertEquals(Optional.empty() ,ingredientRepository.findById(175L));
    }

    @Test
    void saveTest(){
        ingredient = ingredientRepository.findById(ingredientId).get();
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        assertEquals(ingredient.getIngredientId(), savedIngredient.getIngredientId()); //While editing id remains same
        ingredient = new Ingredient();
        ingredient.setName("Ingredient 1");
        savedIngredient = ingredientRepository.save(ingredient);
        assertEquals(9, savedIngredient.getIngredientId()); //While new insertion id should be the next number
    }

    @Test
    void deleteByIdTest(){
        assertEquals(ingredientId, ingredientRepository.findById(ingredientId).get().getIngredientId());
        ingredientRepository.deleteById(ingredientId);
        assertEquals(Optional.empty() ,ingredientRepository.findById(ingredientId));
    }
}