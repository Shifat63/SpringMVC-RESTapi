package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.RecipeDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.RecipeSetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.RecipeDTOToRecipe;
import com.shifat63.springmvcrestapi.api.v1.mapper.RecipeToRecipeDTO;
import com.shifat63.springmvcrestapi.services.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

// Author: Shifat63

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeToRecipeDTO recipeToRecipeDTO;
    private final RecipeDTOToRecipe recipeDTOToRecipe;

    public RecipeController(RecipeService recipeService, RecipeToRecipeDTO recipeToRecipeDTO, RecipeDTOToRecipe recipeDTOToRecipe) {
        this.recipeService = recipeService;
        this.recipeToRecipeDTO = recipeToRecipeDTO;
        this.recipeDTOToRecipe = recipeDTOToRecipe;
    }

    @GetMapping
    public ResponseEntity<RecipeSetDTO> getAllRecipes() throws Exception{
        return new ResponseEntity<RecipeSetDTO>(
                new RecipeSetDTO(recipeToRecipeDTO.convert(recipeService.findAll())), HttpStatus.OK);
    }
}
