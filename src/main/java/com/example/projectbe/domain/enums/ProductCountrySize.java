package com.example.projectbe.domain.enums;

import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ProductCountrySize {
    EU("EU"),US("US"),UK("UK");
    private final String uiRepresentation;

    public static Set<String> allProductCountrySizes() {
        return Stream.of(values())
                .map(ProductCountrySize::getUiRepresentation)
                .collect(Collectors.toSet());
    }

    public static ProductCountrySize of(String uiRepresentation) {
        return Stream.of(values())
                .filter(productCountrySize -> productCountrySize.getUiRepresentation().equals(uiRepresentation))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(ProductCountrySize.class, uiRepresentation));
    }

    ProductCountrySize(String uiRepresentation) {
        this.uiRepresentation = uiRepresentation;
    }
}
