package com.example.projectbe.core.service;

import com.example.projectbe.core.dto.ProductModelCategoryDto;

import java.util.List;

public interface ProductModelCategoryService {

    List<ProductModelCategoryDto> findAllProductCategories();

}
