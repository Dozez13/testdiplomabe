package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.ShoesReview;
import com.example.projectbe.domain.enums.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoesReviewRepository extends JpaRepository<ShoesReview, Long> {

    @Query("SELECT COUNT(sr) FROM ShoesReview sr"
            + " WHERE sr.rating = :rating"
            + " AND sr.shoesModel.id = :shoesModelId")
    Long countByRatingAndShoesModelId(@Param("rating") Rating rating, @Param("shoesModelId") Long shoesModelId);

    @Query("SELECT COUNT(sr) FROM ShoesReview sr"
            + " WHERE sr.shoesModel.id = :shoesModelId")
    Long countAllByShoesModelId(@Param("shoesModelId") Long shoesModelId);

    @Query("SELECT COALESCE(AVG(sr.rating),0.0) FROM ShoesReview sr"
            + " WHERE sr.shoesModel.id = :shoesModelId")
    Double averageRatingByShoesModelId(@Param("shoesModelId") Long shoesModelId);

    @Query("SELECT sr FROM ShoesReview sr"
            + " LEFT JOIN FETCH sr.shoesModel sm"
            + " LEFT JOIN FETCH sm.shoes s"
            + " WHERE s.id = :shoesId ")
    List<ShoesReview> findAllByShoesId(@Param("shoesId") Long shoesId);
}
