package com.example.projectbe.domain.entity;

import com.example.projectbe.domain.enums.ProductCountrySize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    private Double size;

    @Column(name = "product_country_size")
    @Enumerated(value = EnumType.STRING)
    private ProductCountrySize productCountrySize;


}
