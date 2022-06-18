package com.example.projectbe.domain.enums;

import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final Integer rating;

    public static Rating of(Integer ratingValue) {
        return Stream.of(values())
                .filter(rating -> rating.getRating().equals(ratingValue))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(Rating.class, ratingValue.toString()));
    }

    public static Set<Integer> allRatings() {
        return Stream.of(values())
                .map(Rating::getRating)
                .collect(Collectors.toSet());
    }

    Rating(Integer rating) {
        this.rating = rating;
    }
}
