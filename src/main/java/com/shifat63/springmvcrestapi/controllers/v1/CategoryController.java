package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.CatorgorySetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryDTOToCategory;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryToCategoryDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.services.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Author: Shifat63

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;
    private final CategoryDTOToCategory categoryDTOToCategory;
    private final CategoryToCategoryDTO categoryToCategoryDTO;

    public CategoryController(CategoryService categoryService, CategoryDTOToCategory categoryDTOToCategory, CategoryToCategoryDTO categoryToCategoryDTO) {
        this.categoryService = categoryService;
        this.categoryDTOToCategory = categoryDTOToCategory;
        this.categoryToCategoryDTO = categoryToCategoryDTO;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CatorgorySetDTO getAllCategories() throws Exception{
        return new CatorgorySetDTO(categoryToCategoryDTO.convertSet(categoryService.findAll()));
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long id) throws Exception{
        return categoryToCategoryDTO.convert(categoryService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO saveOrUpdateCategory(@RequestBody CategoryDTO categoryDTO) throws Exception{
        Category category = categoryService.saveOrUpdate(categoryDTOToCategory.convert(categoryDTO));
        return categoryToCategoryDTO.convert(category);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id) throws Exception{
        categoryService.deleteById(id);
    }
}
