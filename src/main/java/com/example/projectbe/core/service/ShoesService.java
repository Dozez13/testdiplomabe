package com.example.projectbe.core.service;

import com.example.projectbe.core.dto.*;
import com.example.projectbe.core.dto.generic.ListResponseDto;

import java.util.Set;

public interface ShoesService {
    ListResponseDto<ShoesModelDto> getShoesModel(ShoesModelSearchParameters shoesModelSearchParameters);

    ListResponseDto<ShoesModelDto> findSimilarImages(ShoesSimilarImageDto shoesSimilarImageDto);
    Set<String> findFilteredShoesModelColors(ShoesModelSearchParameters shoesModelSearchParameters);

    Set<String> findFilteredShoesModelTypes(ShoesModelSearchParameters shoesModelSearchParameters);

    Set<ShoesSizeDto> findFilteredShoesModelSizes(ShoesModelSearchParameters shoesModelSearchParameters);

    ShoesModelPriceDto findFilteredShoesModelPrices(ShoesModelSearchParameters shoesModelSearchParameters);

    Set<String> findShoesModelColors();

    Set<String> findShoesModelColorsById(Long shoesModelId);

    Set<String> findShoesModelTypes();

    Set<ShoesSizeDto> findShoesModelSizes();

    Set<ShoesSizeDto> findShoesModelSizesById(Long shoesModelId);

    ShoesModelPriceDto findShoesModelPrices();

    ShoesInfoDto getShoesInfo(Long shoesId);
}
