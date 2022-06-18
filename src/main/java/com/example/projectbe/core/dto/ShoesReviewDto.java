package com.example.projectbe.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoesReviewDto {
    private String firstName;
    private String lastName;
    private Integer rating;
    private LocalDateTime reviewDateTime;
    private String payload;
}
