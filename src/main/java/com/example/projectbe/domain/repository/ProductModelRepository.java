package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductModelRepository extends JpaRepository<ProductModel,Long> {
}
