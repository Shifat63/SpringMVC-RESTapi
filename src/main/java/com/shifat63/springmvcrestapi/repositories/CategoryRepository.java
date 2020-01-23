package com.shifat63.springmvcrestapi.repositories;

import com.shifat63.springmvcrestapi.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Author: Shifat63

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
