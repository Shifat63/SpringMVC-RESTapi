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
import static com.shifat63.springmvcrestapi.controllers.v1.ConvertToXML.asXmlString;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

// Author: Shifat63

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
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipes", hasSize(2)))
                .andExpect(jsonPath("$.recipes[0].recipeId", equalTo(Integer.parseInt(recipeId2.toString()))))
                .andExpect(jsonPath("$.recipes[0].name", equalTo(name2)))
                .andExpect(jsonPath("$.recipes[0].categories", hasSize(1)))
                .andExpect(jsonPath("$.recipes[0].categories[0].categoryId", equalTo(Integer.parseInt(categoryId2.toString()))))
                .andExpect(jsonPath("$.recipes[0].categories[0].name", equalTo(categoryName2)))
                .andExpect(jsonPath("$.recipes[0].ingredients", hasSize(1)))
                .andExpect(jsonPath("$.recipes[0].ingredients[0].ingredientId", equalTo(Integer.parseInt(ingredientId2.toString()))))
                .andExpect(jsonPath("$.recipes[0].ingredients[0].name", equalTo(ingredientName2)))
                .andExpect(jsonPath("$.recipes[1].recipeId", equalTo(Integer.parseInt(recipeId.toString()))))
                .andExpect(jsonPath("$.recipes[1].name", equalTo(name)))
                .andExpect(jsonPath("$.recipes[1].categories", hasSize(1)))
                .andExpect(jsonPath("$.recipes[1].categories[0].categoryId", equalTo(Integer.parseInt(categoryId.toString()))))
                .andExpect(jsonPath("$.recipes[1].categories[0].name", equalTo(categoryName)))
                .andExpect(jsonPath("$.recipes[1].ingredients", hasSize(1)))
                .andExpect(jsonPath("$.recipes[1].ingredients[0].ingredientId", equalTo(Integer.parseInt(ingredientId.toString()))))
                .andExpect(jsonPath("$.recipes[1].ingredients[0].name", equalTo(ingredientName)));

        mockMvc.perform(get(recipeController.BASE_URL)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("RecipeSetDTO/recipes").exists())
                .andExpect(xpath("RecipeSetDTO/recipes/recipes").nodeCount(is(2)))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/recipeId").string(recipeId2.toString()))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/name").string(name2))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/categories/categories").nodeCount(is(1)))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/categories/categories[1]/categoryId").string(categoryId2.toString()))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/categories/categories[1]/name").string(categoryName2))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/ingredients/ingredients").nodeCount(is(1)))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/ingredients/ingredients[1]/ingredientId").string(ingredientId2.toString()))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[1]/ingredients/ingredients[1]/name").string(ingredientName2))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/recipeId").string(recipeId.toString()))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/name").string(name))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/categories/categories").nodeCount(is(1)))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/categories/categories[1]/categoryId").string(categoryId.toString()))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/categories/categories[1]/name").string(categoryName))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/ingredients/ingredients").nodeCount(is(1)))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/ingredients/ingredients[1]/ingredientId").string(ingredientId.toString()))
                .andExpect(xpath("RecipeSetDTO/recipes/recipes[2]/ingredients/ingredients[1]/name").string(ingredientName));

        verify(recipeService, times(2)).findAll();
        verify(recipeToRecipeDTO, times(2)).convertSet(anySet());
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

        mockMvc.perform(get(recipeController.BASE_URL + "/" + recipeId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipeId", equalTo(Integer.parseInt(recipeId.toString()))))
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.categories", hasSize(1)))
                .andExpect(jsonPath("$.categories[0].categoryId", equalTo(Integer.parseInt(categoryId.toString()))))
                .andExpect(jsonPath("$.categories[0].name", equalTo(categoryName)))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].ingredientId", equalTo(Integer.parseInt(ingredientId.toString()))))
                .andExpect(jsonPath("$.ingredients[0].name", equalTo(ingredientName)));

        mockMvc.perform(get(recipeController.BASE_URL + "/" + recipeId)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("RecipeDTO/recipeId").string(recipeId.toString()))
                .andExpect(xpath("RecipeDTO/name").string(name))
                .andExpect(xpath("RecipeDTO/categories/categories").nodeCount(is(1)))
                .andExpect(xpath("RecipeDTO/categories/categories[1]/categoryId").string(categoryId.toString()))
                .andExpect(xpath("RecipeDTO/categories/categories[1]/name").string(categoryName))
                .andExpect(xpath("RecipeDTO/ingredients/ingredients").nodeCount(is(1)))
                .andExpect(xpath("RecipeDTO/ingredients/ingredients[1]/ingredientId").string(ingredientId.toString()))
                .andExpect(xpath("RecipeDTO/ingredients/ingredients[1]/name").string(ingredientName));

        verify(recipeService, times(2)).findById(anyLong());
        verify(recipeToRecipeDTO, times(2)).convert(any());
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
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.recipeId", equalTo(Integer.parseInt(recipeId.toString()))))
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.categories", hasSize(1)))
                .andExpect(jsonPath("$.categories[0].categoryId", equalTo(Integer.parseInt(categoryId.toString()))))
                .andExpect(jsonPath("$.categories[0].name", equalTo(categoryName)))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].ingredientId", equalTo(Integer.parseInt(ingredientId.toString()))))
                .andExpect(jsonPath("$.ingredients[0].name", equalTo(ingredientName)));

        mockMvc.perform(post(recipeController.BASE_URL)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML)
                .content(asXmlString(recipeDTO)))
                .andExpect(status().isCreated())
                .andExpect(xpath("RecipeDTO/recipeId").string(recipeId.toString()))
                .andExpect(xpath("RecipeDTO/name").string(name))
                .andExpect(xpath("RecipeDTO/categories/categories").nodeCount(is(1)))
                .andExpect(xpath("RecipeDTO/categories/categories[1]/categoryId").string(categoryId.toString()))
                .andExpect(xpath("RecipeDTO/categories/categories[1]/name").string(categoryName))
                .andExpect(xpath("RecipeDTO/ingredients/ingredients").nodeCount(is(1)))
                .andExpect(xpath("RecipeDTO/ingredients/ingredients[1]/ingredientId").string(ingredientId.toString()))
                .andExpect(xpath("RecipeDTO/ingredients/ingredients[1]/name").string(ingredientName));

        verify(recipeService, times(2)).saveOrUpdate(any());
        verify(recipeDTOToRecipe, times(2)).convert(any());
        verify(recipeToRecipeDTO, times(2)).convert(any());
    }

    @Test
    void deleteRecipeTest() throws Exception {
        mockMvc.perform(delete(recipeController.BASE_URL + "/" + recipeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(delete(recipeController.BASE_URL + "/" + recipeId)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());

        verify(recipeService, times(2)).deleteById(anyLong());
    }

    @Test
    void exceptionTest() throws Exception {

        when(recipeToRecipeDTO.convert(any())).thenThrow(MockitoException.class);

        mockMvc.perform(get(recipeController.BASE_URL + "/2312")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(recipeController.BASE_URL + "/2312")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isBadRequest());
    }
}