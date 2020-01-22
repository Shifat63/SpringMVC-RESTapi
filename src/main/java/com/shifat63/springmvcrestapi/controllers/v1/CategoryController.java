package com.shifat63.springmvcrestapi.controllers.v1;

import com.shifat63.springmvcrestapi.api.v1.dto.CategoryDTO;
import com.shifat63.springmvcrestapi.api.v1.dto.CatorgorySetDTO;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryDTOToCategory;
import com.shifat63.springmvcrestapi.api.v1.mapper.CategoryToCategoryDTO;
import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.services.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

// Author: Shifat63

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDTOToCategory categoryDTOToCategory;
    private final CategoryToCategoryDTO categoryToCategoryDTO;

    public CategoryController(CategoryService categoryService, CategoryDTOToCategory categoryDTOToCategory, CategoryToCategoryDTO categoryToCategoryDTO) {
        this.categoryService = categoryService;
        this.categoryDTOToCategory = categoryDTOToCategory;
        this.categoryToCategoryDTO = categoryToCategoryDTO;
    }

    @GetMapping
    public ResponseEntity<CatorgorySetDTO> getAllCatetories() throws Exception{
        return new ResponseEntity<CatorgorySetDTO>(
                new CatorgorySetDTO(categoryToCategoryDTO.convert(categoryService.findAll())), HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) throws Exception{
        return new ResponseEntity<CategoryDTO>(categoryToCategoryDTO.convert(categoryService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createNewCategory(@RequestBody CategoryDTO categoryDTO) throws Exception{
        Category category = categoryService.saveOrUpdate(categoryDTOToCategory.convert(categoryDTO));
        return new ResponseEntity<CategoryDTO>(categoryToCategoryDTO.convert(category), HttpStatus.CREATED);
    }

//    @PutMapping({"/{id}"})
//    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws Exception{
//        Category category = categoryService.saveOrUpdate(categoryDTOToCategory.convert(categoryDTO));
//        return new ResponseEntity<CategoryDTO>(categoryToCategoryDTO.convert(category), HttpStatus.OK);
//    }
}
