package com.example.projectbe.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoesInfoDto {

    private Long shoesId;

    private List<String> shoesImages;

    private String modelName;

    private Double productPrice;

    private Set<String> colors;

    private Set<String> sizes;

    private Long countFiveStars;

    private Long countFourStars;

    private Long countThreeStars;

    private Long countTwoStars;

    private Long countOneStars;

    private Long countReviews;

    private Double averageRating;

}
