package com.example.projectbe.domain.specification;

import com.example.projectbe.core.dto.ShoesModelSearchParameters;
import com.example.projectbe.domain.entity.*;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ModelType;
import com.example.projectbe.domain.enums.ProductCountrySize;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.Specification.where;

public final class ShoesSpecification {

    public static Specification<Shoes> getFilterOptions(ShoesModelSearchParameters shoesModelSearchParameters) {
        return where(shoesModelSearchParameters.getSearchSortOptions() == null ? null : sortDistinct(shoesModelSearchParameters.getSearchSortOptions())).and(shoesModelSearchParameters.getModelTypes() == null||shoesModelSearchParameters.getModelTypes().isEmpty() ? null : typeEqual(shoesModelSearchParameters.getModelTypes())).and(shoesModelSearchParameters.getColors() == null ||shoesModelSearchParameters.getColors().isEmpty()? null : colorEqual(shoesModelSearchParameters.getColors())).and((shoesModelSearchParameters.getSizes() == null||shoesModelSearchParameters.getSizes().isEmpty()) && (shoesModelSearchParameters.getProductCountrySizes() == null||shoesModelSearchParameters.getProductCountrySizes().isEmpty()) ? null : sizeEqual(shoesModelSearchParameters.getSizes(), shoesModelSearchParameters.getProductCountrySizes())).and(shoesModelSearchParameters.getMinPrice() == null && shoesModelSearchParameters.getMaxPrice() == null ? null : priceBetween(shoesModelSearchParameters.getMinPrice(), shoesModelSearchParameters.getMaxPrice()));
    }

    public static Specification<Shoes> sortDistinct(ShoesModelSearchParameters.SearchSortOptions searchSortOptions) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            query.orderBy(searchSortOptions == ShoesModelSearchParameters.SearchSortOptions.PRICE_ASC ? criteriaBuilder.asc(root.get(Shoes_.PRICE)) : criteriaBuilder.desc(root.get(Shoes_.PRICE)));
            return null;
        };
    }

    private static Specification<Shoes> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Shoes_.PRICE), minPrice, maxPrice);
    }

    private static Specification<Shoes> sizeEqual(List<Double> sizes, List<ProductCountrySize> productCountrySizes) {
        return (root, query, criteriaBuilder) -> {
            Join<Shoes, ShoesSize> shoesSizeJoin = root.join(Shoes_.SHOES_SIZE);
            return criteriaBuilder.and(shoesSizeJoin.get(ShoesSize_.SIZE).in(sizes), shoesSizeJoin.get(ShoesSize_.PRODUCT_COUNTRY_SIZE).in(productCountrySizes));

        };
    }

    private static Specification<Shoes> colorEqual(Set<Color> colors) {
        return (root, query, criteriaBuilder) -> root.get(Shoes_.COLOR).in(colors);
    }

    private static Specification<Shoes> typeEqual(Set<ModelType> modelTypes) {
        return (root, query, criteriaBuilder) -> {
            Join<Shoes, ShoesModel> shoesShoesModelJoin = root.join(Shoes_.SHOES_MODEL);
            Join<ShoesModel, ShoesModelType> shoesModelShoesModelTypeJoin = shoesShoesModelJoin.join(ShoesModel_.SHOES_MODEL_TYPE);
            return shoesModelShoesModelTypeJoin.get(ShoesModelType_.MODEL_TYPE).in(modelTypes);
        };
    }
}

