package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.ShoesModelTypeCreationDto;
import com.example.projectbe.core.dto.ShoesModelTypeDto;
import com.example.projectbe.core.util.ImageUtil;
import com.example.projectbe.domain.entity.ShoesModelType;
import org.springframework.stereotype.Component;

@Component
public class ShoesModelTypeMapper {

    public ShoesModelTypeDto toShoesModelTypeDto(ShoesModelType shoesModelType) {
        ShoesModelTypeDto shoesModelTypeDto = new ShoesModelTypeDto();
        shoesModelTypeDto.setTypeName(shoesModelType.getModelType().getUiRepresentation());
        shoesModelTypeDto.setImage(ImageUtil.base64ImageFromFilePath(shoesModelType.getPath()));
        return shoesModelTypeDto;
    }

    public ShoesModelType fromShoesModelTypeCreationDto(ShoesModelTypeCreationDto shoesModelTypeCreationDto) {
        ShoesModelType shoesModelType = new ShoesModelType();
        shoesModelType.setId(shoesModelTypeCreationDto.getId());
        return shoesModelType;
    }
    public ShoesModelTypeCreationDto toShoesModelTypeCreationDto(ShoesModelType ShoesModelType) {
        ShoesModelTypeCreationDto shoesModelTypeCreationDto = new ShoesModelTypeCreationDto();
        shoesModelTypeCreationDto.setId(ShoesModelType.getId());
        shoesModelTypeCreationDto.setTypeName(ShoesModelType.getModelType().getUiRepresentation());
        return shoesModelTypeCreationDto;
    }
}
