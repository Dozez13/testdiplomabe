package com.example.projectbe.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ShoesModelType extends ProductModelType {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoes_model_category_id")
    private ShoesModelCategory shoesModelCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoesModelType")
    private List<ShoesModel> shoesModel;
}
