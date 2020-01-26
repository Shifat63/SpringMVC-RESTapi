package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.RecipeDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.RecipeSetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.RecipeDTOToRecipe;
import com.shifat63.springmvcrestapi.api.v1.mapper.RecipeToRecipeDTO;
import com.shifat63.springmvcrestapi.domain.Recipe;
import com.shifat63.springmvcrestapi.services.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Author: Shifat63

@Api(value = "Handle Recipe related operations")
@RestController
@RequestMapping(RecipeController.BASE_URL)
public class RecipeController {

    public static final String BASE_URL = "/api/v1/recipes";

    private final RecipeService recipeService;
    private final RecipeToRecipeDTO recipeToRecipeDTO;
    private final RecipeDTOToRecipe recipeDTOToRecipe;

    public RecipeController(RecipeService recipeService, RecipeToRecipeDTO recipeToRecipeDTO, RecipeDTOToRecipe recipeDTOToRecipe) {
        this.recipeService = recipeService;
        this.recipeToRecipeDTO = recipeToRecipeDTO;
        this.recipeDTOToRecipe = recipeDTOToRecipe;
    }

    @ApiOperation(value = "Get all recipes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RecipeSetDTO getAllRecipes() throws Exception{
        return new RecipeSetDTO(recipeToRecipeDTO.convertSet(recipeService.findAll()));
    }

    @ApiOperation(value = "Get a certain recipe based on id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public RecipeDTO getRecipeById(@PathVariable Long id) throws Exception{
        return recipeToRecipeDTO.convert(recipeService.findById(id));
    }

    @ApiOperation(value = "Insert or update a recipe", notes = "For new recipe id can either be zero or not given. For update id must be given.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO saveOrUpdateRecipe(@RequestBody RecipeDTO recipeDTO) throws Exception{
        Recipe recipe = recipeService.saveOrUpdate(recipeDTOToRecipe.convert(recipeDTO));
        return recipeToRecipeDTO.convert(recipe);
    }

    @ApiOperation(value = "Delete a certain recipe based on id")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(@PathVariable Long id) throws Exception{
        recipeService.deleteById(id);
    }
}
