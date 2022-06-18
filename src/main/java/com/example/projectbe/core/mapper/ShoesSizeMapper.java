package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.ShoesSizeDto;
import com.example.projectbe.domain.entity.ShoesSize;
import org.springframework.stereotype.Component;

@Component
public class ShoesSizeMapper {

    public ShoesSizeDto toShoesSizeDto(ShoesSize shoesSize){
        ShoesSizeDto shoesSizeDto = new ShoesSizeDto();
        shoesSizeDto.setSize(shoesSize.getSize());
        shoesSizeDto.setCountryType(shoesSize.getProductCountrySize().getUiRepresentation());
        return shoesSizeDto;
    }
}
