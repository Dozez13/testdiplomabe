package com.example.projectbe.web.controller;

import com.example.projectbe.core.dto.ProductModelCategoryDto;
import com.example.projectbe.core.dto.generic.ListResponseDto;
import com.example.projectbe.core.service.ProductModelCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure/categories")
@RequiredArgsConstructor
public class ProductModelCategoryController {

    private final ProductModelCategoryService productModelCategoryService;

    @GetMapping
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ProductModelCategoryDto> getAllCategories() {
        ListResponseDto<ProductModelCategoryDto> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(productModelCategoryService.findAllProductCategories());
        return listResponseDto;
    }

}
