package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.RecipeDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.RecipeDTOToRecipe;
import com.shifat63.springmvcrestapi.api.v1.mapper.RecipeToRecipeDTO;
import com.shifat63.springmvcrestapi.controllers.ExceptionHandlerController;
import com.shifat63.springmvcrestapi.services.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static com.shifat63.springmvcrestapi.controllers.v1.ConvertToJson.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    RecipeDTOToRecipe recipeDTOToRecipe;
    @Mock
    RecipeToRecipeDTO recipeToRecipeDTO;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    private static final Long recipeId = 1L;
    private String name = "recipe 1";
    private static final Long recipeId2 = 2L;
    private String name2 = "recipe 2";
    private static final Long categoryId = 1L;
    private String categoryName = "category 1";
    private static final Long categoryId2 = 2L;
    private String categoryName2 = "category 2";
    private static final Long ingredientId = 1L;
    private String ingredientName = "ingredient 1";
    private static final Long ingredientId2 = 2L;
    private String ingredientName2 = "ingredient 2";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    void getAllRecipesTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        CategoryDTO categoryDTO2 = new CategoryDTO();
        Set<CategoryDTO> categoryDTOs = new HashSet<>();
        Set<CategoryDTO> categoryDTOs2 = new HashSet<>();
        IngredientDTO ingredientDTO = new IngredientDTO();
        IngredientDTO ingredientDTO2 = new IngredientDTO();
        Set<IngredientDTO> ingredientDTOs = new HashSet<>();
        Set<IngredientDTO> ingredientDTOs2 = new HashSet<>();

        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(categoryName);
        categoryDTOs.add(categoryDTO);
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(ingredientName);
        ingredientDTOs.add(ingredientDTO);
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipeId);
        recipeDTO.setName(name);
        recipeDTO.setCategories(categoryDTOs);
        recipeDTO.setIngredients(ingredientDTOs);

        categoryDTO2.setCategoryId(categoryId2);
        categoryDTO2.setName(categoryName2);
        categoryDTOs2.add(categoryDTO2);
        ingredientDTO2.setIngredientId(ingredientId2);
        ingredientDTO2.setName(ingredientName2);
        ingredientDTOs2.add(ingredientDTO2);
        RecipeDTO recipeDTO2 = new RecipeDTO();
        recipeDTO2.setRecipeId(recipeId2);
        recipeDTO2.setName(name2);
        recipeDTO2.setCategories(categoryDTOs2);
        recipeDTO2.setIngredients(ingredientDTOs2);

        Set<RecipeDTO> recipeDTOSet = new HashSet<>();
        recipeDTOSet.add(recipeDTO);
        recipeDTOSet.add(recipeDTO2);

        when(recipeToRecipeDTO.convertSet(anySet())).thenReturn(recipeDTOSet);

        mockMvc.perform(get(recipeController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipes", hasSize(2)))
                .andExpect(jsonPath("$.recipes[0].recipeId", equalTo(2)))
                .andExpect(jsonPath("$.recipes[0].name", equalTo(name2)))
                .andExpect(jsonPath("$.recipes[0].categories", hasSize(1)))
                .andExpect(jsonPath("$.recipes[0].categories[0].categoryId", equalTo(2)))
                .andExpect(jsonPath("$.recipes[0].categories[0].name", equalTo(categoryName2)))
                .andExpect(jsonPath("$.recipes[0].ingredients", hasSize(1)))
                .andExpect(jsonPath("$.recipes[0].ingredients[0].ingredientId", equalTo(2)))
                .andExpect(jsonPath("$.recipes[0].ingredients[0].name", equalTo(ingredientName2)))
                .andExpect(jsonPath("$.recipes[1].recipeId", equalTo(1)))
                .andExpect(jsonPath("$.recipes[1].name", equalTo(name)))
                .andExpect(jsonPath("$.recipes[1].categories", hasSize(1)))
                .andExpect(jsonPath("$.recipes[1].categories[0].categoryId", equalTo(1)))
                .andExpect(jsonPath("$.recipes[1].categories[0].name", equalTo(categoryName)))
                .andExpect(jsonPath("$.recipes[1].ingredients", hasSize(1)))
                .andExpect(jsonPath("$.recipes[1].ingredients[0].ingredientId", equalTo(1)))
                .andExpect(jsonPath("$.recipes[1].ingredients[0].name", equalTo(ingredientName)));

        verify(recipeService, times(1)).findAll();
        verify(recipeToRecipeDTO, times(1)).convertSet(anySet());
    }

    @Test
    void getRecipeByIdTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        Set<CategoryDTO> categoryDTOs = new HashSet<>();
        IngredientDTO ingredientDTO = new IngredientDTO();
        Set<IngredientDTO> ingredientDTOs = new HashSet<>();

        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(categoryName);
        categoryDTOs.add(categoryDTO);
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(ingredientName);
        ingredientDTOs.add(ingredientDTO);
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipeId);
        recipeDTO.setName(name);
        recipeDTO.setCategories(categoryDTOs);
        recipeDTO.setIngredients(ingredientDTOs);

        when(recipeToRecipeDTO.convert(any())).thenReturn(recipeDTO);

        mockMvc.perform(get(recipeController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipeId", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.categories", hasSize(1)))
                .andExpect(jsonPath("$.categories[0].categoryId", equalTo(1)))
                .andExpect(jsonPath("$.categories[0].name", equalTo(categoryName)))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].ingredientId", equalTo(1)))
                .andExpect(jsonPath("$.ingredients[0].name", equalTo(ingredientName)));

        verify(recipeService, times(1)).findById(anyLong());
        verify(recipeToRecipeDTO, times(1)).convert(any());
    }

    @Test
    void saveOrUpdateRecipeTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        Set<CategoryDTO> categoryDTOs = new HashSet<>();
        IngredientDTO ingredientDTO = new IngredientDTO();
        Set<IngredientDTO> ingredientDTOs = new HashSet<>();

        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(categoryName);
        categoryDTOs.add(categoryDTO);
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(ingredientName);
        ingredientDTOs.add(ingredientDTO);
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipeId);
        recipeDTO.setName(name);
        recipeDTO.setCategories(categoryDTOs);
        recipeDTO.setIngredients(ingredientDTOs);

        when(recipeToRecipeDTO.convert(any())).thenReturn(recipeDTO);

        mockMvc.perform(post(recipeController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.recipeId", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.categories", hasSize(1)))
                .andExpect(jsonPath("$.categories[0].categoryId", equalTo(1)))
                .andExpect(jsonPath("$.categories[0].name", equalTo(categoryName)))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].ingredientId", equalTo(1)))
                .andExpect(jsonPath("$.ingredients[0].name", equalTo(ingredientName)));

        verify(recipeService, times(1)).saveOrUpdate(any());
        verify(recipeDTOToRecipe, times(1)).convert(any());
        verify(recipeToRecipeDTO, times(1)).convert(any());
    }

    @Test
    void deleteRecipeTest() throws Exception {
        mockMvc.perform(delete(recipeController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(recipeService, times(1)).deleteById(anyLong());
    }

    @Test
    void exceptionTest() throws Exception {

        when(recipeToRecipeDTO.convert(any())).thenThrow(MockitoException.class);

        mockMvc.perform(get(recipeController.BASE_URL + "/2312")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}