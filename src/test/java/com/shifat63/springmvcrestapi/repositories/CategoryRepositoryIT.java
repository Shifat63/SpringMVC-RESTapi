package com.shifat63.springmvcrestapi.repositories;

import com.shifat63.springmvcrestapi.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoryRepositoryIT {
    @Autowired
    CategoryRepository categoryRepository;

    Long categoryId = 1L;
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void findAllTest(){
        int counter = 0;
        for (Object i : categoryRepository.findAll()) {
            counter++;
        }
        assertEquals(4, counter);
    }

    @Test
    void findByIdTest(){
        assertEquals(categoryId, categoryRepository.findById(categoryId).get().getCategoryId());
        assertEquals(Optional.empty() ,categoryRepository.findById(5L));
    }

    @Test
    void saveTest(){
        category = categoryRepository.findById(categoryId).get();
        Category savedCategory = categoryRepository.save(category);
        assertEquals(category.getCategoryId(), savedCategory.getCategoryId()); //While editing id remains same
        category = new Category();
        category.setName("Category 1");
        savedCategory = categoryRepository.save(category);
        assertEquals(5, savedCategory.getCategoryId()); //While new insertion id should be the next number
    }

    @Test
    void deleteByIdTest(){
        assertEquals(categoryId, categoryRepository.findById(categoryId).get().getCategoryId());
        categoryRepository.deleteById(categoryId);
        assertEquals(Optional.empty() ,categoryRepository.findById(categoryId));
    }
}