package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
