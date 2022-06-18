package com.example.projectbe.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoesModelCreationDto {
    private String modelName;
    private ShoesModelTypeCreationDto type;
    List<ShoesCreationDto> products;

}
