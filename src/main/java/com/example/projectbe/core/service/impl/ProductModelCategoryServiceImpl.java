package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.ProductModelCategoryDto;
import com.example.projectbe.core.mapper.ProductModelCategoryMapper;
import com.example.projectbe.core.service.ProductModelCategoryService;
import com.example.projectbe.domain.repository.ProductModelCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductModelCategoryServiceImpl implements ProductModelCategoryService {

    private final ProductModelCategoryMapper productModelCategoryMapper;

    private final ProductModelCategoryRepository productModelCategoryRepository;

    @Override
    public List<ProductModelCategoryDto> findAllProductCategories() {
        return productModelCategoryRepository.findAll()
                .stream()
                .map(productModelCategoryMapper::toProductModelCategoryDto)
                .collect(Collectors.toList());
    }
}
