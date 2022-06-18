package com.example.projectbe.domain.entity;

import com.example.projectbe.domain.enums.Color;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Shoes extends Product {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoes_size_id")
    private ShoesSize shoesSize;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoes_model_id")
    private ShoesModel shoesModel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoes", cascade = CascadeType.ALL)
    private List<ShoesImage> shoesImages;
}
