package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.IngredientSetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.IngredientDTOToIngredient;
import com.shifat63.springmvcrestapi.api.v1.mapper.IngredientToIngredientDTO;
import com.shifat63.springmvcrestapi.domain.Ingredient;
import com.shifat63.springmvcrestapi.services.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Author: Shifat63

@RestController
@RequestMapping(IngredientController.BASE_URL)
public class IngredientController {

    public static final String BASE_URL = "/api/v1/ingredients";

    private final IngredientService ingredientService;
    private final IngredientToIngredientDTO ingredientToIngredientDTO;
    private final IngredientDTOToIngredient ingredientDTOToIngredient;

    public IngredientController(IngredientService ingredientService, IngredientToIngredientDTO ingredientToIngredientDTO, IngredientDTOToIngredient ingredientDTOToIngredient) {
        this.ingredientService = ingredientService;
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
        this.ingredientDTOToIngredient = ingredientDTOToIngredient;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public IngredientSetDTO getAllIngredients() throws Exception{
        return new IngredientSetDTO(ingredientToIngredientDTO.convertSet(ingredientService.findAll()));
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public IngredientDTO getIngredientById(@PathVariable Long id) throws Exception{
        return ingredientToIngredientDTO.convert(ingredientService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDTO saveOrUpdateIngredient(@RequestBody IngredientDTO ingredientDTO) throws Exception{
        Ingredient ingredient = ingredientService.saveOrUpdate(ingredientDTOToIngredient.convert(ingredientDTO));
        return ingredientToIngredientDTO.convert(ingredient);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteIngredient(@PathVariable Long id) throws Exception{
        ingredientService.deleteById(id);
    }
}
