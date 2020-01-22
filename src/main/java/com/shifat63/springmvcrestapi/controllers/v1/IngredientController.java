package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.IngredientSetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.IngerdientDTOToIngredient;
import com.shifat63.springmvcrestapi.api.v1.mapper.IngerdientToIngredientDTO;
import com.shifat63.springmvcrestapi.services.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

// Author: Shifat63

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngerdientToIngredientDTO ingerdientToIngredientDTO;
    private final IngerdientDTOToIngredient ingerdientDTOToIngredient;

    public IngredientController(IngredientService ingredientService, IngerdientToIngredientDTO ingerdientToIngredientDTO, IngerdientDTOToIngredient ingerdientDTOToIngredient) {
        this.ingredientService = ingredientService;
        this.ingerdientToIngredientDTO = ingerdientToIngredientDTO;
        this.ingerdientDTOToIngredient = ingerdientDTOToIngredient;
    }

    @GetMapping
    public ResponseEntity<IngredientSetDTO> getAllIngredients() throws Exception{
        return new ResponseEntity<IngredientSetDTO>(
                new IngredientSetDTO(ingerdientToIngredientDTO.convert(ingredientService.findAll())), HttpStatus.OK);
    }
}
