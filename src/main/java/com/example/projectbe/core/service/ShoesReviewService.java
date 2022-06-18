package com.example.projectbe.core.service;

import com.example.projectbe.core.dto.ShoesInfoDto;
import com.example.projectbe.core.dto.ShoesReviewCreateDto;
import com.example.projectbe.core.dto.ShoesReviewDto;
import com.example.projectbe.domain.entity.User;
import com.example.projectbe.domain.enums.Rating;

import java.util.List;

public interface ShoesReviewService {


    List<ShoesReviewDto> getShoesReviewByShoesId(Long shoesId);
    Long reviewCountByRatingAndShoesModelId(Rating rating, Long shoesModelId);

    Long reviewCountByShoesModelId(Long shoesModelId);

    Double averageRatingByShoesModelId(Long shoesModelId);

    void createReview(ShoesReviewCreateDto shoesReviewCreateDto, User user);

}
