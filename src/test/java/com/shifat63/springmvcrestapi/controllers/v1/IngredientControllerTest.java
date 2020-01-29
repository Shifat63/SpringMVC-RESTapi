package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.IngredientDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.IngredientDTOToIngredient;
import com.shifat63.springmvcrestapi.api.v1.mapper.IngredientToIngredientDTO;
import com.shifat63.springmvcrestapi.controllers.ExceptionHandlerController;
import com.shifat63.springmvcrestapi.services.service.IngredientService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Author: Shifat63

class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;
    @Mock
    IngredientDTOToIngredient ingredientDTOToIngredient;
    @Mock
    IngredientToIngredientDTO ingredientToIngredientDTO;

    @InjectMocks
    IngredientController ingredientController;

    MockMvc mockMvc;

    private static final Long ingredientId = 1L;
    private String name = "ingredient 1";
    private static final Long ingredientId2 = 2L;
    private String name2 = "ingredient 2";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    void getAllIngredientsTest() throws Exception {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(name);

        IngredientDTO ingredientDTO2 = new IngredientDTO();
        ingredientDTO2.setIngredientId(ingredientId2);
        ingredientDTO2.setName(name2);

        Set<IngredientDTO> ingredientDTOSet = new HashSet<>();
        ingredientDTOSet.add(ingredientDTO);
        ingredientDTOSet.add(ingredientDTO2);

        when(ingredientToIngredientDTO.convertSet(anySet())).thenReturn(ingredientDTOSet);

        mockMvc.perform(get(ingredientController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredients", hasSize(2)))
                .andExpect(jsonPath("$.ingredients[0].ingredientId", equalTo(Integer.parseInt(ingredientId.toString()))))
                .andExpect(jsonPath("$.ingredients[0].name", equalTo(name)))
                .andExpect(jsonPath("$.ingredients[1].ingredientId", equalTo(Integer.parseInt(ingredientId2.toString()))))
                .andExpect(jsonPath("$.ingredients[1].name", equalTo(name2)));

        mockMvc.perform(get(ingredientController.BASE_URL)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("IngredientSetDTO/ingredients").exists())
                .andExpect(xpath("IngredientSetDTO/ingredients/ingredients").nodeCount(is(2)))
                .andExpect(xpath("IngredientSetDTO/ingredients/ingredients[1]/ingredientId").string(ingredientId.toString()))
                .andExpect(xpath("IngredientSetDTO/ingredients/ingredients[1]/name").string(name))
                .andExpect(xpath("IngredientSetDTO/ingredients/ingredients[2]/ingredientId").string(ingredientId2.toString()))
                .andExpect(xpath("IngredientSetDTO/ingredients/ingredients[2]/name").string(name2));

        verify(ingredientService, times(2)).findAll();
        verify(ingredientToIngredientDTO, times(2)).convertSet(anySet());
    }

    @Test
    void getIngredientByIdTest() throws Exception {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(name);

        when(ingredientToIngredientDTO.convert(any())).thenReturn(ingredientDTO);

        mockMvc.perform(get(ingredientController.BASE_URL + "/" + ingredientId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredientId", equalTo(Integer.parseInt(ingredientId.toString()))))
                .andExpect(jsonPath("$.name", equalTo(name)));

        mockMvc.perform(get(ingredientController.BASE_URL + "/" + ingredientId)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("IngredientDTO").exists())
                .andExpect(xpath("IngredientDTO/ingredientId").string(ingredientId.toString()))
                .andExpect(xpath("IngredientDTO/name").string(name));

        verify(ingredientService, times(2)).findById(anyLong());
        verify(ingredientToIngredientDTO, times(2)).convert(any());
    }

    @Test
    void saveOrUpdateIngredientTest() throws Exception {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredientId(ingredientId);
        ingredientDTO.setName(name);

        when(ingredientToIngredientDTO.convert(any())).thenReturn(ingredientDTO);

        mockMvc.perform(post(ingredientController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ingredientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ingredientId", equalTo(Integer.parseInt(ingredientId.toString()))))
                .andExpect(jsonPath("$.name", equalTo(name)));

        mockMvc.perform(post(ingredientController.BASE_URL)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML)
                .content(asXmlString(ingredientDTO)))
                .andExpect(status().isCreated())
                .andExpect(xpath("IngredientDTO").exists())
                .andExpect(xpath("IngredientDTO/ingredientId").string(ingredientId.toString()))
                .andExpect(xpath("IngredientDTO/name").string(name));

        verify(ingredientService, times(2)).saveOrUpdate(any());
        verify(ingredientDTOToIngredient, times(2)).convert(any());
        verify(ingredientToIngredientDTO, times(2)).convert(any());
    }

    @Test
    void deleteIngredientTest() throws Exception {
        mockMvc.perform(delete(ingredientController.BASE_URL + "/" + ingredientId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(delete(ingredientController.BASE_URL + "/" + ingredientId)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());

        verify(ingredientService, times(2)).deleteById(anyLong());
    }

    @Test
    void exceptionTest() throws Exception {

        when(ingredientToIngredientDTO.convert(any())).thenThrow(MockitoException.class);

        mockMvc.perform(get(ingredientController.BASE_URL + "/2312")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(ingredientController.BASE_URL + "/2312")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isBadRequest());
    }
}