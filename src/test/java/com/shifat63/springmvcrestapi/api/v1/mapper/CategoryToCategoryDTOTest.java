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

class CategoryToCategoryDTOTest {
    private static final Long categoryId = 1L;
    private String name = "category 1";
    private static final Long categoryId2 = 2L;
    private String name2 = "category 2";
    Category category = new Category();
    Category category2 = new Category();
    Set<Category> CategorySet = new HashSet<>();
    CategoryToCategoryDTO categoryToCategoryDTO = new CategoryToCategoryDTO();

    @BeforeEach
    void setUp() {
        category.setCategoryId(categoryId);
        category.setName(name);

        category2.setCategoryId(categoryId2);
        category2.setName(name2);

        CategorySet.add(category);
        CategorySet.add(category2);
    }

    @Test
    void convertTest() {
        CategoryDTO categoryDTO = categoryToCategoryDTO.convert(category);
        assertEquals(categoryId, categoryDTO.getCategoryId());
        assertEquals(name, categoryDTO.getName());
    }

    @Test
    void convertSetTest() {
        Set<CategoryDTO> categoryDTOSet = categoryToCategoryDTO.convertSet(CategorySet);
        assertEquals(2, categoryDTOSet.size());

        List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>(categoryDTOSet);
        assertEquals(categoryId, categoryDTOList.get(0).getCategoryId());
        assertEquals(name, categoryDTOList.get(0).getName());
        assertEquals(categoryId2, categoryDTOList.get(1).getCategoryId());
        assertEquals(name2, categoryDTOList.get(1).getName());
    }
}