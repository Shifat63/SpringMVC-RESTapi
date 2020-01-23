package com.shifat63.springmvcrestapi.services.serviceImplementation;

import com.shifat63.springmvcrestapi.domain.Category;
import com.shifat63.springmvcrestapi.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    Category category = new Category();
    Set<Category> categories = new HashSet<>();

    private static final Long categoryId = 1L;
    private String name = "category 1";
    private static final Long categoryId2 = 2L;
    private String name2 = "category 2";

    @BeforeEach
    void setUp() {
        category.setCategoryId(categoryId);
        category.setName(name);
        categories.add(category);
    }

    @Test
    void findAllTest() throws Exception {
        Category category2 = new Category();
        category2.setCategoryId(categoryId2);
        category2.setName(name2);
        categories.add(category2);
        when(categoryRepository.findAll()).thenReturn(categories);
        assertNotNull(categoryService.findAll());
        assertEquals(2, categoryService.findAll().size());
        verify(categoryRepository, times(2)).findAll();
    }

    @Test
    void findByIdTest() throws Exception {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        assertNotNull(categoryService.findById(categoryId));
        assertEquals(categoryId, categoryService.findById(categoryId).getCategoryId());
        verify(categoryRepository, times(2)).findById(categoryId);
    }

    @Test
    void saveOrUpdateTest() throws Exception {
        when(categoryRepository.save(any())).thenReturn(category);
        assertNotNull(categoryService.saveOrUpdate(category));
        assertEquals(categoryId, categoryService.saveOrUpdate(category).getCategoryId());
        verify(categoryRepository, times(2)).save(category);
    }

    @Test
    void deleteByIdTest() {
        categoryRepository.deleteById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}