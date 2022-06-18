package com.example.projectbe.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
public class ShoesModel extends ProductModel {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoesModel",cascade = CascadeType.ALL)
    private List<Shoes> shoes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoesModel")
    private List<ShoesReview> shoesReviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoes_model_type_id")
    private ShoesModelType shoesModelType;

}
