package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

// Author: Shifat63

class CategoryDTOToCategoryTest {
    private static final Long categoryId = 1L;
    private String name = "category 1";
    private static final Long categoryId2 = 2L;
    private String name2 = "category 2";
    CategoryDTO categoryDTO = new CategoryDTO();
    CategoryDTO categoryDTO2 = new CategoryDTO();
    Set<CategoryDTO> CategoryDTOSet = new HashSet<>();
    CategoryDTOToCategory categoryDTOToCategory = new CategoryDTOToCategory();

    @BeforeEach
    void setUp() {
        categoryDTO.setCategoryId(categoryId);
        categoryDTO.setName(name);

        categoryDTO2.setCategoryId(categoryId2);
        categoryDTO2.setName(name2);

        CategoryDTOSet.add(categoryDTO);
        CategoryDTOSet.add(categoryDTO2);
    }

    @Test
    void convertTest() {
        Category category = categoryDTOToCategory.convert(categoryDTO);
        assertEquals(categoryId, category.getCategoryId());
        assertEquals(name, category.getName());
    }

    @Test
    void convertSetTest() {
        Set<Category> categorySet = categoryDTOToCategory.convertSet(CategoryDTOSet);
        assertEquals(2, categorySet.size());

        List<Category> categoryList = new ArrayList<Category>(categorySet);
        assertEquals(categoryId, categoryList.get(0).getCategoryId());
        assertEquals(name, categoryList.get(0).getName());
        assertEquals(categoryId2, categoryList.get(1).getCategoryId());
        assertEquals(name2, categoryList.get(1).getName());
    }
}