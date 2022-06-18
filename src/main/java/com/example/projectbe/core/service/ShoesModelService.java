package com.example.projectbe.core.service;

import com.example.projectbe.core.dto.ShoesCreationVariationDto;
import com.example.projectbe.core.dto.ShoesModelCreationDto;
import com.example.projectbe.core.dto.ShoesModelDto;
import com.example.projectbe.core.dto.ShoesModelSearchParameters;
import com.example.projectbe.core.dto.generic.ListResponseDto;
import com.example.projectbe.domain.entity.ShoesModel;

import java.util.Set;

public interface ShoesModelService {
    void createShoesModel(ShoesModelCreationDto shoesModelCreationDto);


    ShoesCreationVariationDto createShoesModel();

    ShoesModel findShoesModelByShoesId(Long shoesId);


}
