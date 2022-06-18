package com.example.projectbe.web.controller;

import com.example.projectbe.core.dto.ShoesModelTypeCreationDto;
import com.example.projectbe.core.dto.ShoesModelTypeDto;
import com.example.projectbe.core.dto.generic.ListResponseDto;
import com.example.projectbe.core.service.ShoesModelTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure/shoes-model-types")
@RequiredArgsConstructor
public class ShoesModelTypeController {

    private final ShoesModelTypeService shoesModelTypeService;

    @GetMapping
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesModelTypeDto> getAllShoesTypes() {
        ListResponseDto<ShoesModelTypeDto> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(shoesModelTypeService.findAllShoesTypes());
        return listResponseDto;
    }

    @GetMapping(value = "/shoes-create")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesModelTypeCreationDto> getAllShoesTypesWithId() {
        ListResponseDto<ShoesModelTypeCreationDto> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(shoesModelTypeService.findAllShoesModelTypes());
        return listResponseDto;
    }


}
