package com.example.projectbe.core.service;

import com.example.projectbe.core.dto.ShoesModelTypeCreationDto;
import com.example.projectbe.core.dto.ShoesModelTypeDto;

import java.util.List;

public interface ShoesModelTypeService {

    List<ShoesModelTypeDto> findAllShoesTypes();

    List<ShoesModelTypeCreationDto> findAllShoesModelTypes();


}
