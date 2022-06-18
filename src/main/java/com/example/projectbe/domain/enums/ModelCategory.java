package com.example.projectbe.domain.enums;

import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ModelCategory {
    SHOES("Shoes");
    private final String uiRepresentation;

    public static ModelCategory of(String uiRepresentation) {
        return Stream.of(values())
                .filter(modelCategory -> modelCategory.getUiRepresentation().equals(uiRepresentation))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(ModelCategory.class, uiRepresentation));
    }

    public static Set<String> allModelCategories() {
        return Stream.of(values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    ModelCategory(String uiRepresentation) {
        this.uiRepresentation = uiRepresentation;
    }
}
