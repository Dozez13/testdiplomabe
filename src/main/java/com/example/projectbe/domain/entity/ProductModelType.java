package com.example.projectbe.domain.entity;

import com.example.projectbe.domain.enums.ModelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductModelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_type")
    @Enumerated(value = EnumType.STRING)
    private ModelType modelType;

    @Column(name = "path_to_file")
    private String path;


}
