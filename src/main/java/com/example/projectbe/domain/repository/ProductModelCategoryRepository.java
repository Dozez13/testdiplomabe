package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.ProductModelCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductModelCategoryRepository extends JpaRepository<ProductModelCategory, Long> {
}
