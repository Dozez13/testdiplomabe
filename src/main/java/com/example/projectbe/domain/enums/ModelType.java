package com.example.projectbe.domain.enums;

import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ModelType {
    ALL_SHOES("All Shoes"), BOOTS("Boots"), FLATS("Flats"), SANDALS("Sandals"), SLIPPERS("Slippers"), SNEAKERS("Sneakers"), WEDGES("Wedges");
    private final String uiRepresentation;

    public static Set<String> allModelTypes() {
        return Stream.of(values())
                .map(ModelType::getUiRepresentation)
                .collect(Collectors.toSet());
    }

    public static ModelType of(String uiRepresentation) {
        return Stream.of(values())
                .filter(modelType -> modelType.getUiRepresentation().equals(uiRepresentation))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(ModelType.class, uiRepresentation));
    }

    ModelType(String uiRepresentation) {
        this.uiRepresentation = uiRepresentation;
    }
}
