package com.example.projectbe.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ShoesModelCategory extends ProductModelCategory {


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoesModelCategory")
    private List<ShoesModelType> shoesModelTypes;
}
