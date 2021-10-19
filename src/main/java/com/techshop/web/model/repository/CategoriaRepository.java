package com.techshop.web.model.repository;

import com.techshop.web.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Category, Integer> {

}