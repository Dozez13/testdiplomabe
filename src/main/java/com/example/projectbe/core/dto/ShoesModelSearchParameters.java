package com.example.projectbe.core.dto;

import com.example.projectbe.domain.entity.Product;
import com.example.projectbe.domain.entity.Shoes;
import com.example.projectbe.domain.entity.ShoesModel;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ModelType;
import com.example.projectbe.domain.enums.ProductCountrySize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoesModelSearchParameters {
    private Set<ModelType> modelTypes;
    private List<Double> sizes;
    private List<ProductCountrySize> productCountrySizes;
    private Set<Color> colors;
    private Double minPrice;
    private Double maxPrice;
    private SearchSortOptions searchSortOptions;

    public enum SearchSortOptions {
        PRICE_ASC() {
            @Override
            public Comparator<ShoesModel> getComparatorForShoesModel() {
                return Comparator.comparing(shoesModelFirst -> shoesModelFirst.getShoes().stream()
                        .min(Comparator.comparing(Product::getPrice)).orElseThrow(() -> new EntityNotFoundException("Shoes ont found")).getPrice());
            }

            @Override
            public Comparator<Shoes> getComparatorForShoes() {
                return Comparator.comparing(Product::getPrice);
            }
        }, PRICE_DESC() {
            @Override
            public Comparator<ShoesModel> getComparatorForShoesModel() {
                return Collections.reverseOrder(Comparator.comparing(shoesModelFirst -> shoesModelFirst.getShoes().stream()
                        .max(Comparator.comparing(Product::getPrice)).orElseThrow(() -> new EntityNotFoundException("Shoes ont found")).getPrice()));
            }

            @Override
            public Comparator<Shoes> getComparatorForShoes() {
                return Collections.reverseOrder(PRICE_ASC.getComparatorForShoes());
            }
        };

        public static SearchSortOptions of(String name) {
            return Stream.of(values())
                    .filter(modelType -> modelType.name().equals(name))
                    .findAny()
                    .orElseThrow(() -> new EnumConstantNotPresentException(ModelType.class, name));
        }

        public abstract Comparator<ShoesModel> getComparatorForShoesModel();

        public abstract Comparator<Shoes> getComparatorForShoes();


    }
}
