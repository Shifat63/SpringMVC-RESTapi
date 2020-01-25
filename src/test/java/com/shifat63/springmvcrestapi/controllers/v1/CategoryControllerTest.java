package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryDTOToCategory;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryToCategoryDTO;
import com.shifat63.springmvcrestapi.controllers.ExceptionHandlerController;
import com.shifat63.springmvcrestapi.services.service.CategoryService;
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
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Author: Shifat63

class CategoryControllerTest {

    @Mock
    CategoryService categoryService;
    @Mock
    CategoryDTOToCategory categoryDTOToCategory;
    @Mock
    CategoryToCategoryDTO categoryToCategoryDTO;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    private static final Long categoryId = 1L;
    private String name = "category 1";
    private static final Long categoryId2 = 2L;
    private String name2 = "category 2";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    void getAllCategoriesTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(name);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setCategoryId(categoryId2);
        categoryDTO2.setName(name2);

        Set<CategoryDTO> categoryDTOSet = new HashSet<>();
        categoryDTOSet.add(categoryDTO);
        categoryDTOSet.add(categoryDTO2);

        when(categoryToCategoryDTO.convertSet(anySet())).thenReturn(categoryDTOSet);

        mockMvc.perform(get(categoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)))
                .andExpect(jsonPath("$.categories[0].categoryId", equalTo(1)))
                .andExpect(jsonPath("$.categories[0].name", equalTo(name)))
                .andExpect(jsonPath("$.categories[1].categoryId", equalTo(2)))
                .andExpect(jsonPath("$.categories[1].name", equalTo(name2)));

        verify(categoryService, times(1)).findAll();
        verify(categoryToCategoryDTO, times(1)).convertSet(anySet());
    }

    @Test
    void getCategoryByIdTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(name);

        when(categoryToCategoryDTO.convert(any())).thenReturn(categoryDTO);

        mockMvc.perform(get(categoryController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(name)));

        verify(categoryService, times(1)).findById(anyLong());
        verify(categoryToCategoryDTO, times(1)).convert(any());
    }

    @Test
    void saveOrUpdateCategoryTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(name);

        when(categoryToCategoryDTO.convert(any())).thenReturn(categoryDTO);

        mockMvc.perform(post(categoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryId", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(name)));

        verify(categoryService, times(1)).saveOrUpdate(any());
        verify(categoryDTOToCategory, times(1)).convert(any());
        verify(categoryToCategoryDTO, times(1)).convert(any());
    }

    @Test
    void deleteCategoryTest() throws Exception {
        mockMvc.perform(delete(categoryController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).deleteById(anyLong());
    }

    @Test
    void exceptionTest() throws Exception {

        when(categoryToCategoryDTO.convert(any())).thenThrow(MockitoException.class);

        mockMvc.perform(get(categoryController.BASE_URL + "/2312")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}