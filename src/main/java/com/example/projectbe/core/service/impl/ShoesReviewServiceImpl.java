package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.ShoesReviewCreateDto;
import com.example.projectbe.core.dto.ShoesReviewDto;
import com.example.projectbe.core.mapper.ShoesReviewMapper;
import com.example.projectbe.core.service.ShoesModelService;
import com.example.projectbe.core.service.ShoesReviewService;
import com.example.projectbe.core.service.UserService;
import com.example.projectbe.domain.entity.ShoesReview;
import com.example.projectbe.domain.entity.User;
import com.example.projectbe.domain.enums.Rating;
import com.example.projectbe.domain.repository.ShoesReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoesReviewServiceImpl implements ShoesReviewService {

    private final ShoesReviewMapper shoesReviewMapper;
    private final ShoesReviewRepository shoesReviewRepository;

    private final ShoesModelService shoesModelService;


    public List<ShoesReviewDto> getShoesReviewByShoesId(Long shoesId) {
        return shoesReviewRepository.findAllByShoesId(shoesId).stream().map(shoesReviewMapper::toShoesReviewDto).collect(Collectors.toList());
    }

    @Override
    public Long reviewCountByRatingAndShoesModelId(Rating rating, Long shoesModelId) {
        return shoesReviewRepository.countByRatingAndShoesModelId(rating, shoesModelId);
    }

    @Override
    public Long reviewCountByShoesModelId(Long shoesModelId) {
        return shoesReviewRepository.countAllByShoesModelId(shoesModelId);
    }

    @Override
    public Double averageRatingByShoesModelId(Long shoesModelId) {
        return shoesReviewRepository.averageRatingByShoesModelId(shoesModelId);
    }

    @Override
    public void createReview(ShoesReviewCreateDto shoesReviewCreateDto, User user) {
        ShoesReview shoesReview = new ShoesReview();
        shoesReview.setShoesModel(shoesModelService.findShoesModelByShoesId(shoesReviewCreateDto.getShoesId()));
        shoesReview.setPayload(shoesReviewCreateDto.getPayload());
        shoesReview.setReviewDatetime(LocalDateTime.now());
        shoesReview.setRating(Rating.of(shoesReviewCreateDto.getRating()));
        shoesReview.setUser(user);
        shoesReviewRepository.save(shoesReview);
    }
}
