package com.example.projectbe.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoesReviewCreateDto {

    private Long shoesId;
    private String payload;
    private Integer rating;

}
