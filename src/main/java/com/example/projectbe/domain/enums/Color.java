package com.example.projectbe.domain.enums;

import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Color {
    WHITE("WHITE"), BLACK("BLACK"), BLUE("BLUE"), GREEN("GREEN"), YELLOW("YELLOW");
    private final String uiRepresentation;

    public static Color of(String uiRepresentation) {
        return Stream.of(values())
                .filter(shoesType -> shoesType.getUiRepresentation().equals(uiRepresentation))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(Color.class, uiRepresentation));
    }

    public static Set<String> allColors() {
        return Stream.of(values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    Color(String uiRepresentation) {
        this.uiRepresentation = uiRepresentation;
    }
}
