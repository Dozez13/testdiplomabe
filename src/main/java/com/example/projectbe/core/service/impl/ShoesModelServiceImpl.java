package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.ShoesCreationVariationDto;
import com.example.projectbe.core.dto.ShoesModelCreationDto;
import com.example.projectbe.core.mapper.ShoesModelMapper;
import com.example.projectbe.core.service.ShoesModelService;
import com.example.projectbe.domain.entity.ShoesModel;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ProductCountrySize;
import com.example.projectbe.domain.repository.ShoesModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ShoesModelServiceImpl implements ShoesModelService {

    private final ShoesModelRepository shoesModelRepository;

    private final ShoesModelMapper shoesModelMapper;

    @Override
    public void createShoesModel(ShoesModelCreationDto shoesModelCreationDto) {
        ShoesModel shoesModel = shoesModelMapper.fromShoesModelCreationDto(shoesModelCreationDto);
        shoesModelRepository.save(shoesModel);

    }

    @Override
    public ShoesCreationVariationDto createShoesModel() {
        ShoesCreationVariationDto creationVariationDto = new ShoesCreationVariationDto();
        creationVariationDto.setColors(new ArrayList<>(Color.allColors()));
        creationVariationDto.setProductCountryTypes(new ArrayList<>(ProductCountrySize.allProductCountrySizes()));
        return creationVariationDto;
    }

    @Override
    public ShoesModel findShoesModelByShoesId(Long shoesId) {
        return shoesModelRepository.findByShoesId(shoesId);
    }


}
