package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.CatorgorySetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryDTOToCategory;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryToCategoryDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.services.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Author: Shifat63

@Api(value = "Handle Category related operations")
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

    @ApiOperation(value = "Get all categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CatorgorySetDTO getAllCategories() throws Exception{
        return new CatorgorySetDTO(categoryToCategoryDTO.convertSet(categoryService.findAll()));
    }

    @ApiOperation(value = "Get a certain category based on id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long id) throws Exception{
        return categoryToCategoryDTO.convert(categoryService.findById(id));
    }

    @ApiOperation(value = "Insert or update a category", notes = "For new category id can either be zero or not given. For update id must be given.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO saveOrUpdateCategory(@RequestBody CategoryDTO categoryDTO) throws Exception{
        Category category = categoryService.saveOrUpdate(categoryDTOToCategory.convert(categoryDTO));
        return categoryToCategoryDTO.convert(category);
    }

    @ApiOperation(value = "Delete a certain category based on id")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id) throws Exception{
        categoryService.deleteById(id);
    }
}
