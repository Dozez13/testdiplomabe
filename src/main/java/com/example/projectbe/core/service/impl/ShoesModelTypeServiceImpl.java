package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.ShoesModelTypeCreationDto;
import com.example.projectbe.core.dto.ShoesModelTypeDto;
import com.example.projectbe.core.mapper.ShoesModelTypeMapper;
import com.example.projectbe.core.service.ShoesModelTypeService;
import com.example.projectbe.domain.entity.ProductModelType;
import com.example.projectbe.domain.enums.ModelType;
import com.example.projectbe.domain.repository.ShoesModelTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoesModelTypeServiceImpl implements ShoesModelTypeService {

    private final ShoesModelTypeMapper shoesModelTypeMapper;

    private final ShoesModelTypeRepository shoesModelTypeRepository;

    @Override
    public List<ShoesModelTypeDto> findAllShoesTypes() {
        return shoesModelTypeRepository.findAll()
                .stream()
                .map(shoesModelTypeMapper::toShoesModelTypeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShoesModelTypeCreationDto> findAllShoesModelTypes() {
        return shoesModelTypeRepository.findAll()
                .stream()
                .filter(shoesModelType -> !shoesModelType.getModelType().equals(ModelType.ALL_SHOES))
                .map(shoesModelTypeMapper::toShoesModelTypeCreationDto)
                .collect(Collectors.toList());
    }


}
