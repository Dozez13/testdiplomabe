package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.ShoesReviewDto;
import com.example.projectbe.domain.entity.ShoesReview;
import org.springframework.stereotype.Component;

@Component
public class ShoesReviewMapper {

    public ShoesReviewDto toShoesReviewDto(ShoesReview shoesReview) {
        ShoesReviewDto shoesReviewDto = new ShoesReviewDto();
        shoesReviewDto.setFirstName(shoesReview.getUser().getFirstName());
        shoesReviewDto.setLastName(shoesReview.getUser().getLastName());
        shoesReviewDto.setReviewDateTime(shoesReview.getReviewDatetime());
        shoesReviewDto.setRating(shoesReview.getRating().getRating());
        shoesReviewDto.setPayload(shoesReview.getPayload());
        return shoesReviewDto;

    }
}
