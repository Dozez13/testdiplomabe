package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.*;
import com.example.projectbe.core.dto.generic.ListResponseDto;
import com.example.projectbe.core.mapper.ShoesMapper;
import com.example.projectbe.core.mapper.ShoesSizeMapper;
import com.example.projectbe.core.service.ShoesReviewService;
import com.example.projectbe.core.service.ShoesService;
import com.example.projectbe.core.util.ImageUtil;
import com.example.projectbe.domain.entity.ProductModelType;
import com.example.projectbe.domain.entity.Shoes;
import com.example.projectbe.domain.entity.ShoesImage;
import com.example.projectbe.domain.entity.ShoesModel;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ModelType;
import com.example.projectbe.domain.enums.Rating;
import com.example.projectbe.domain.repository.ShoesRepository;
import com.example.projectbe.domain.specification.ShoesSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoesServiceImpl implements ShoesService {
    private final ShoesMapper shoesMapper;

    private final ShoesSizeMapper shoesSizeMapper;
    private final ShoesRepository shoesRepository;

    private final ShoesReviewService shoesReviewService;

    @Override
    @Transactional
    public ListResponseDto<ShoesModelDto> getShoesModel(ShoesModelSearchParameters shoesModelSearchParameters) {
        ListResponseDto<ShoesModelDto> shoesModelDtoListResponseDto = new ListResponseDto<>();
        shoesModelDtoListResponseDto.setElements(new ArrayList<>(shoesRepository.findAll(ShoesSpecification.getFilterOptions(shoesModelSearchParameters))
                .stream().map(shoesMapper::toShoesModelDto).collect(Collectors.toSet())));
        return shoesModelDtoListResponseDto;
    }

    @Override
    @Transactional
    public ListResponseDto<ShoesModelDto> findSimilarImages(ShoesSimilarImageDto shoesSimilarImageDto) {
        ListResponseDto<ShoesModelDto> shoesModelDtoListResponseDto = new ListResponseDto<>();
        shoesModelDtoListResponseDto.setElements(new ArrayList<>(shoesRepository.findAll()
                .stream()
                .filter(shoes -> ImageUtil.hasSimilarImages(shoesSimilarImageDto, shoes.getShoesImages().stream().map(ShoesImage::getPath).collect(Collectors.toList())))
                .map(shoesMapper::toShoesModelDto).collect(Collectors.toSet())));
        return shoesModelDtoListResponseDto;
    }

    @Override
    public Set<String> findFilteredShoesModelColors(ShoesModelSearchParameters shoesModelSearchParameters) {
        return shoesRepository.findAll(ShoesSpecification.getFilterOptions(shoesModelSearchParameters))
                .stream().map(Shoes::getColor).map(Color::getUiRepresentation).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findFilteredShoesModelTypes(ShoesModelSearchParameters shoesModelSearchParameters) {
        return shoesRepository.findAll(ShoesSpecification.getFilterOptions(shoesModelSearchParameters))
                .stream().map(Shoes::getShoesModel).map(ShoesModel::getShoesModelType).map(ProductModelType::getModelType).map(ModelType::getUiRepresentation).collect(Collectors.toSet());
    }

    @Override
    public Set<ShoesSizeDto> findFilteredShoesModelSizes(ShoesModelSearchParameters shoesModelSearchParameters) {
        return shoesRepository.findAll(ShoesSpecification.getFilterOptions(shoesModelSearchParameters)).stream().map(Shoes::getShoesSize)
                .map(shoesSizeMapper::toShoesSizeDto).collect(Collectors.toSet());
    }

    @Override
    public ShoesModelPriceDto findFilteredShoesModelPrices(ShoesModelSearchParameters shoesModelSearchParameters) {
        return shoesMapper.toShoesModelPriceDto(shoesRepository.findAll(ShoesSpecification.getFilterOptions(shoesModelSearchParameters)));
    }

    @Override
    public Set<String> findShoesModelColors() {
        return shoesRepository.findAll()
                .stream().map(Shoes::getColor).map(Color::getUiRepresentation).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findShoesModelColorsById(Long shoesModelId) {
        return shoesRepository.findAllByShoesModelId(shoesModelId)
                .stream().map(Shoes::getColor).map(Color::getUiRepresentation).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findShoesModelTypes() {
        return shoesRepository.findAll()
                .stream().map(Shoes::getShoesModel).map(ShoesModel::getShoesModelType).map(ProductModelType::getModelType).map(ModelType::getUiRepresentation).collect(Collectors.toSet());
    }

    @Override
    public Set<ShoesSizeDto> findShoesModelSizes() {
        return shoesRepository.findAll().stream().map(Shoes::getShoesSize)
                .map(shoesSizeMapper::toShoesSizeDto).collect(Collectors.toSet());
    }

    @Override
    public Set<ShoesSizeDto> findShoesModelSizesById(Long shoesModelId) {
        return shoesRepository.findAllByShoesModelId(shoesModelId).stream().map(Shoes::getShoesSize)
                .map(shoesSizeMapper::toShoesSizeDto).collect(Collectors.toSet());
    }

    @Override
    public ShoesModelPriceDto findShoesModelPrices() {
        return shoesMapper.toShoesModelPriceDto(shoesRepository.findAll());
    }

    @Override
    public ShoesInfoDto getShoesInfo(Long shoesId) {
        Shoes shoes = shoesRepository.findById(shoesId).orElseThrow(() -> new EntityNotFoundException("Shoes not found"));
        ShoesInfoDto shoesInfoDto = shoesMapper.toShoesInfoDto(shoes);
        shoesInfoDto.setColors(findShoesModelColorsById(shoes.getShoesModel().getId()));
        shoesInfoDto.setSizes(findShoesModelSizesById(shoes.getShoesModel().getId()).stream().map(shoesSizeDto -> shoesSizeDto.getCountryType() + " " + shoesSizeDto.getSize()).collect(Collectors.toSet()));
        shoesInfoDto.setCountReviews(shoesReviewService.reviewCountByShoesModelId(shoes.getShoesModel().getId()));
        shoesInfoDto.setCountFiveStars(shoesReviewService.reviewCountByRatingAndShoesModelId(Rating.FIVE, shoes.getShoesModel().getId()));
        shoesInfoDto.setCountFourStars(shoesReviewService.reviewCountByRatingAndShoesModelId(Rating.FOUR, shoes.getShoesModel().getId()));
        shoesInfoDto.setCountThreeStars(shoesReviewService.reviewCountByRatingAndShoesModelId(Rating.THREE, shoes.getShoesModel().getId()));
        shoesInfoDto.setCountTwoStars(shoesReviewService.reviewCountByRatingAndShoesModelId(Rating.TWO, shoes.getShoesModel().getId()));
        shoesInfoDto.setCountOneStars(shoesReviewService.reviewCountByRatingAndShoesModelId(Rating.ONE, shoes.getShoesModel().getId()));
        shoesInfoDto.setAverageRating(shoesReviewService.averageRatingByShoesModelId(shoes.getShoesModel().getId()));
        return shoesInfoDto;
    }
}
