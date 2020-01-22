package com.shifat63.springmvcrestapi.services.serviceImplementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import com.shifat63.springmvcrestapi.repositories.IngredientRepository;
import com.shifat63.springmvcrestapi.services.service.IngredientService;

import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<Ingredient> findAll() throws Exception {
        log.info("start: findAll method of IngredientServiceImpl");
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientRepository.findAll().forEach(ingredientSet::add);
        log.info("end: findAll method of IngredientServiceImpl");
        return ingredientSet;
    }

    @Override
    public Ingredient findById(Long ingredientId) throws Exception {
        log.info("start: findById method of IngredientServiceImpl");
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();
        log.info("end: findById method of IngredientServiceImpl");
        return ingredient;
    }

    @Override
    public Ingredient saveOrUpdate(Ingredient ingredient) throws Exception {
        log.info("start: saveOrUpdate method of IngredientServiceImpl");
        ingredient = ingredientRepository.save(ingredient);
        log.info("end: saveOrUpdate method of IngredientServiceImpl");
        return ingredient;
    }

    @Override
    public void deleteById(Long ingredientId) throws Exception {
        log.info("start: deleteById method of IngredientServiceImpl");
        ingredientRepository.deleteById(ingredientId);
        log.info("end: deleteById method of IngredientServiceImpl");
    }
}
