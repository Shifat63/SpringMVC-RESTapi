package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Component
public class CategoryToCategoryDTO {
    public CategoryDTO convert(Category category)
    {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Set<CategoryDTO> convertSet(Set<Category> categories)
    {
        Set<CategoryDTO> CategoryDTOs = new HashSet<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getCategoryId());
            categoryDTO.setName(category.getName());
            CategoryDTOs.add(categoryDTO);
        }

        return CategoryDTOs;
    }
}
