package com.shifat63.springmvcrestapi.services.serviceImplementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.repositories.CategoryRepository;
import com.shifat63.springmvcrestapi.services.service.CategoryService;

import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() throws Exception {
        log.info("start: findAll method of CategoryServiceImpl");
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findAll().forEach(categorySet::add);
        log.info("end: findAll method of CategoryServiceImpl");
        return categorySet;
    }

    @Override
    public Category findById(Long categoryId) throws Exception {
        log.info("start: findById method of CategoryServiceImpl");
        Category category = categoryRepository.findById(categoryId).get();
        log.info("end: findById method of CategoryServiceImpl");
        return category;
    }

    @Override
    public Category saveOrUpdate(Category category) throws Exception {
        log.info("start: saveOrUpdate method of CategoryServiceImpl");
        category = categoryRepository.save(category);
        log.info("end: saveOrUpdate method of CategoryServiceImpl");
        return category;
    }

    @Override
    public void deleteById(Long categoryId) throws Exception {
        log.info("start: deleteById method of CategoryServiceImpl");
        categoryRepository.deleteById(categoryId);
        log.info("end: deleteById method of CategoryServiceImpl");
    }
}
