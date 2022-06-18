package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.ProductModelCategoryDto;
import com.example.projectbe.core.util.ImageUtil;
import com.example.projectbe.domain.entity.ProductModelCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductModelCategoryMapper {

    public ProductModelCategoryDto toProductModelCategoryDto(ProductModelCategory productModelCategory) {
        ProductModelCategoryDto productModelCategoryDtoToFill = new ProductModelCategoryDto();
        productModelCategoryDtoToFill.setCategoryName(productModelCategory.getModelCategory().getUiRepresentation());
        productModelCategoryDtoToFill.setImage(ImageUtil.base64ImageFromFilePath(productModelCategory.getPath()));
        return productModelCategoryDtoToFill;
    }
}
