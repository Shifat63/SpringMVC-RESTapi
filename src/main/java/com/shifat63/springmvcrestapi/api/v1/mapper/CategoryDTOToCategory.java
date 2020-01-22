package com.shifat63.springmvcrestapi.api.v1.mapper;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Component
public class CategoryDTOToCategory {
    public Category convert(CategoryDTO categoryDTO)
    {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public Set<Category> convert(Set<CategoryDTO> categoryDTOs)
    {
        Set<Category> categories = new HashSet<>();

        for (CategoryDTO categoryDTO : categoryDTOs) {
            Category category = new Category();
            category.setCategoryId(categoryDTO.getCategoryId());
            category.setName(categoryDTO.getName());
            categories.add(category);
        }

        return categories;
    }
}
