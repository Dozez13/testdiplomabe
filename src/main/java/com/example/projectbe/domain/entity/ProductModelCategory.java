package com.example.projectbe.domain.entity;

import com.example.projectbe.domain.enums.ModelCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductModelCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_category")
    @Enumerated(EnumType.STRING)
    private ModelCategory modelCategory;

    @Column(name = "path_to_file")
    private String path;

}
