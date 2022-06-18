package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.*;
import com.example.projectbe.core.util.ImageUtil;
import com.example.projectbe.domain.entity.Shoes;
import com.example.projectbe.domain.entity.ShoesImage;
import com.example.projectbe.domain.entity.ShoesModel;
import com.example.projectbe.domain.entity.ShoesSize;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ModelCategory;
import com.example.projectbe.domain.enums.ModelType;
import com.example.projectbe.domain.enums.ProductCountrySize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ShoesModelMapper {

    private final ShoesModelTypeMapper shoesModelTypeMapper;


    public ShoesModel fromShoesModelCreationDto(ShoesModelCreationDto shoesModelCreationDto) {
        List<ShoesCreationDto> shoesCreationDtos = shoesModelCreationDto.getProducts();
        ShoesModelTypeCreationDto shoesModelTypeCreationDto = shoesModelCreationDto.getType();
        ShoesModel shoesModel = new ShoesModel();
        shoesModel.setName(shoesModelCreationDto.getModelName());
        List<Shoes> shoes = shoesCreationDtos.stream()
                .map(shoesCreationDto -> {
                    Shoes shoe = new Shoes();
                    shoe.setShoesModel(shoesModel);
                    ShoesSize shoesSize = new ShoesSize();
                    shoesSize.setSize(shoesCreationDto.getSize().getSize());
                    shoesSize.setProductCountrySize(ProductCountrySize.of(shoesCreationDto.getSize().getCountryType()));
                    shoesSize.setShoesList(Collections.singletonList(shoe));
                    shoe.setShoesSize(shoesSize);
                    shoe.setColor(Color.of(shoesCreationDto.getColor()));
                    shoe.setPrice(shoesCreationDto.getPrice());
                    shoe.setShoesImages(IntStream.range(0, shoesCreationDto.getImages().size())
                            .mapToObj(i -> {
                                ShoesImage image = new ShoesImage();
                                image.setShoes(shoe);
                                image.setPath(ImageUtil.createImageAndReturnPath(shoesModelCreationDto.getModelName(), ModelCategory.SHOES, ModelType.of(shoesModelTypeCreationDto.getTypeName()), Color.of(shoesCreationDto.getColor()), shoesCreationDto.getSize(), shoesCreationDto.getPrice(), i, shoesCreationDto.getImages().get(i)));
                                return image;
                            }).collect(Collectors.toList()));
                    return shoe;
                }).collect(Collectors.toList());
        shoesModel.setShoes(shoes);
        shoesModel.setShoesModelType(shoesModelTypeMapper.fromShoesModelTypeCreationDto(shoesModelTypeCreationDto));
        return shoesModel;
    }
}
