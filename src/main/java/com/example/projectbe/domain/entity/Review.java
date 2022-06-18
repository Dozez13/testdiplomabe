package com.example.projectbe.domain.entity;

import com.example.projectbe.domain.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payload")
    private String payload;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rating")
    private Rating rating;

    @Column(name = "review_datetime")
    @CreationTimestamp
    private LocalDateTime reviewDatetime;
}
