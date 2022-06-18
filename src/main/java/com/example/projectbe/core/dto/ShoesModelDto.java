package com.example.projectbe.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "modelName")
public class ShoesModelDto {

    private Long id;

    private String modelName;

    private Double productPrice;

    private String image;

}
