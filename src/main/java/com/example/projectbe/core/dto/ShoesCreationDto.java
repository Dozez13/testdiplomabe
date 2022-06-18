package com.example.projectbe.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoesCreationDto {
    private List<String> images;
    private ShoesSizeDto size;
    private Double price;
    private String color;

}
