package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.ShoesInfoDto;
import com.example.projectbe.core.dto.ShoesModelDto;
import com.example.projectbe.core.dto.ShoesModelPriceDto;
import com.example.projectbe.core.util.ImageUtil;
import com.example.projectbe.domain.entity.Product;
import com.example.projectbe.domain.entity.Shoes;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoesMapper {

    public ShoesModelDto toShoesModelDto(Shoes shoes) {
        ShoesModelDto shoesModelDto = new ShoesModelDto();
        shoesModelDto.setId(shoes.getId());
        shoesModelDto.setModelName(shoes.getShoesModel().getName());
        shoesModelDto.setProductPrice(shoes.getPrice());
        shoesModelDto.setImage(ImageUtil.base64ImageFromFilePath(shoes.getShoesImages().stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Shoes image not found")).getPath()));
        return shoesModelDto;
    }

    public ShoesInfoDto toShoesInfoDto(Shoes shoes) {
        ShoesInfoDto shoesInfoDto = new ShoesInfoDto();
        shoesInfoDto.setShoesId(shoes.getId());
        shoesInfoDto.setModelName(shoes.getShoesModel().getName());
        shoesInfoDto.setProductPrice(shoes.getPrice());
        shoesInfoDto.setShoesImages(shoes.getShoesImages().stream().map(shoesImage -> ImageUtil.base64ImageFromFilePath(shoesImage.getPath())).collect(Collectors.toList()));
        return shoesInfoDto;
    }

    public ShoesModelPriceDto toShoesModelPriceDto(List<Shoes> shoes) {
        ShoesModelPriceDto shoesModelPriceDto = new ShoesModelPriceDto();
        shoesModelPriceDto.setMinPrice(shoes.stream().map(Product::getPrice).min(Double::compareTo).orElse(0.0));
        shoesModelPriceDto.setMaxPrice(shoes.stream().map(Product::getPrice).max(Double::compareTo).orElse(0.0));
        return shoesModelPriceDto;
    }
}
