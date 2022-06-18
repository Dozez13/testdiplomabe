package com.example.projectbe.web.controller;


import com.example.projectbe.core.dto.*;
import com.example.projectbe.core.dto.generic.ListResponseDto;
import com.example.projectbe.core.service.ShoesModelService;
import com.example.projectbe.core.service.ShoesService;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ModelType;
import com.example.projectbe.domain.enums.ProductCountrySize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/secure/shoes-models")
@RequiredArgsConstructor
public class ShoesModelController {

    private final ShoesModelService shoesModelService;
    private final ShoesService shoesService;


    @PostMapping
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoesModel(@RequestBody ShoesModelCreationDto shoesModelCreationDto) {

        shoesModelService.createShoesModel(shoesModelCreationDto);

    }

    @GetMapping(value = "/create")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    @ResponseStatus(HttpStatus.OK)
    public ShoesCreationVariationDto createShoesModel() {
        return shoesModelService.createShoesModel();

    }

    @PostMapping(value = "/similar")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesModelDto> getShoesModels(@RequestBody ShoesSimilarImageDto base64Image) {
        return shoesService.findSimilarImages(base64Image);
    }

    @GetMapping
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesModelDto> getShoesModels(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                                         @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                                         @RequestParam(value = "colors", required = false) Set<String> colors,
                                                         @RequestParam(value = "sizes", required = false) List<Double> sizes,
                                                         @RequestParam(value = "sizeCountryTypes", required = false) List<String> sizeCountryTypes,
                                                         @RequestParam(value = "types", required = false) Set<String> types,
                                                         @RequestParam(value = "sort", required = false) String sort) {
        ShoesModelSearchParameters shoesModelSearchParameters = new ShoesModelSearchParameters();
        shoesModelSearchParameters.setModelTypes(types == null || types.isEmpty() ? null : types.stream().map(ModelType::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setColors(colors == null || colors.isEmpty() ? null : colors.stream().map(Color::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setSearchSortOptions(sort == null || sort.isEmpty() ? null : ShoesModelSearchParameters.SearchSortOptions.of(sort));
        shoesModelSearchParameters.setMinPrice(minPrice);
        shoesModelSearchParameters.setMaxPrice(maxPrice);
        shoesModelSearchParameters.setSizes(sizes);
        shoesModelSearchParameters.setProductCountrySizes(sizeCountryTypes == null || sizeCountryTypes.isEmpty() ? null : sizeCountryTypes.stream().map(ProductCountrySize::of).collect(Collectors.toList()));
        return shoesService.getShoesModel(shoesModelSearchParameters);
    }


    @GetMapping(value = "/filter/colors/include")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<String> getFilteredShoesModelColors(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                                               @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                                               @RequestParam(value = "colors", required = false) Set<String> colors,
                                                               @RequestParam(value = "sizes", required = false) List<Double> sizes,
                                                               @RequestParam(value = "sizeCountryTypes", required = false) List<String> sizeCountryTypes,
                                                               @RequestParam(value = "types", required = false) Set<String> types,
                                                               @RequestParam(value = "sort", required = false) String sort) {
        ShoesModelSearchParameters shoesModelSearchParameters = new ShoesModelSearchParameters();
        shoesModelSearchParameters.setModelTypes(types == null ? null : types.stream().map(ModelType::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setColors(colors == null ? null : colors.stream().map(Color::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setSearchSortOptions(sort == null ? null : ShoesModelSearchParameters.SearchSortOptions.of(sort));
        shoesModelSearchParameters.setMinPrice(minPrice);
        shoesModelSearchParameters.setMaxPrice(maxPrice);
        shoesModelSearchParameters.setSizes(sizes);
        shoesModelSearchParameters.setProductCountrySizes(sizeCountryTypes == null ? null : sizeCountryTypes.stream().map(ProductCountrySize::of).collect(Collectors.toList()));
        ListResponseDto<String> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(new ArrayList<>(shoesService.findFilteredShoesModelColors(shoesModelSearchParameters)));
        return listResponseDto;
    }

    @GetMapping(value = "/filter/types/include")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<String> getFilteredShoesModelTypes(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                                              @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                                              @RequestParam(value = "colors", required = false) Set<String> colors,
                                                              @RequestParam(value = "sizes", required = false) List<Double> sizes,
                                                              @RequestParam(value = "sizeCountryTypes", required = false) List<String> sizeCountryTypes,
                                                              @RequestParam(value = "types", required = false) Set<String> types,
                                                              @RequestParam(value = "sort", required = false) String sort) {
        ShoesModelSearchParameters shoesModelSearchParameters = new ShoesModelSearchParameters();
        shoesModelSearchParameters.setModelTypes(types == null ? null : types.stream().map(ModelType::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setColors(colors == null ? null : colors.stream().map(Color::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setSearchSortOptions(sort == null ? null : ShoesModelSearchParameters.SearchSortOptions.of(sort));
        shoesModelSearchParameters.setMinPrice(minPrice);
        shoesModelSearchParameters.setMaxPrice(maxPrice);
        shoesModelSearchParameters.setSizes(sizes);
        shoesModelSearchParameters.setProductCountrySizes(sizeCountryTypes == null ? null : sizeCountryTypes.stream().map(ProductCountrySize::of).collect(Collectors.toList()));
        ListResponseDto<String> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(new ArrayList<>(shoesService.findFilteredShoesModelTypes(shoesModelSearchParameters)));
        return listResponseDto;
    }

    @GetMapping(value = "/filter/sizes/include")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesSizeDto> getFilteredShoesModelSizes(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                                                    @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                                                    @RequestParam(value = "colors", required = false) Set<String> colors,
                                                                    @RequestParam(value = "sizes", required = false) List<Double> sizes,
                                                                    @RequestParam(value = "sizeCountryTypes", required = false) List<String> sizeCountryTypes,
                                                                    @RequestParam(value = "types", required = false) Set<String> types,
                                                                    @RequestParam(value = "sort", required = false) String sort) {
        ShoesModelSearchParameters shoesModelSearchParameters = new ShoesModelSearchParameters();
        shoesModelSearchParameters.setModelTypes(types == null ? null : types.stream().map(ModelType::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setColors(colors == null ? null : colors.stream().map(Color::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setSearchSortOptions(sort == null ? null : ShoesModelSearchParameters.SearchSortOptions.of(sort));
        shoesModelSearchParameters.setMinPrice(minPrice);
        shoesModelSearchParameters.setMaxPrice(maxPrice);
        shoesModelSearchParameters.setSizes(sizes);
        shoesModelSearchParameters.setProductCountrySizes(sizeCountryTypes == null ? null : sizeCountryTypes.stream().map(ProductCountrySize::of).collect(Collectors.toList()));
        ListResponseDto<ShoesSizeDto> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(new ArrayList<>(shoesService.findFilteredShoesModelSizes(shoesModelSearchParameters)));
        return listResponseDto;
    }

    @GetMapping(value = "/filter/prices/include")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ShoesModelPriceDto getFilteredShoesModelPrices(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                                          @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                                          @RequestParam(value = "colors", required = false) Set<String> colors,
                                                          @RequestParam(value = "sizes", required = false) List<Double> sizes,
                                                          @RequestParam(value = "sizeCountryTypes", required = false) List<String> sizeCountryTypes,
                                                          @RequestParam(value = "types", required = false) Set<String> types,
                                                          @RequestParam(value = "sort", required = false) String sort) {
        ShoesModelSearchParameters shoesModelSearchParameters = new ShoesModelSearchParameters();
        shoesModelSearchParameters.setModelTypes(types == null ? null : types.stream().map(ModelType::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setColors(colors == null ? null : colors.stream().map(Color::of).collect(Collectors.toSet()));
        shoesModelSearchParameters.setSearchSortOptions(sort == null ? null : ShoesModelSearchParameters.SearchSortOptions.of(sort));
        shoesModelSearchParameters.setMinPrice(minPrice);
        shoesModelSearchParameters.setMaxPrice(maxPrice);
        shoesModelSearchParameters.setSizes(sizes);
        shoesModelSearchParameters.setProductCountrySizes(sizeCountryTypes == null ? null : sizeCountryTypes.stream().map(ProductCountrySize::of).collect(Collectors.toList()));
        return shoesService.findFilteredShoesModelPrices(shoesModelSearchParameters);

    }

    @GetMapping(value = "/filter/colors")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<String> getShoesModelColors() {
        ListResponseDto<String> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(new ArrayList<>(shoesService.findShoesModelColors()));
        return listResponseDto;
    }

    @GetMapping(value = "/filter/types")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<String> getShoesModelTypes() {
        ListResponseDto<String> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(new ArrayList<>(shoesService.findShoesModelTypes()));
        return listResponseDto;
    }

    @GetMapping(value = "/filter/sizes")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesSizeDto> getShoesModelSizes() {
        ListResponseDto<ShoesSizeDto> listResponseDto = new ListResponseDto<>();
        listResponseDto.setElements(new ArrayList<>(shoesService.findShoesModelSizes()));
        return listResponseDto;
    }

    @GetMapping(value = "/filter/prices")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ShoesModelPriceDto getShoesModelPrices() {
        return shoesService.findShoesModelPrices();

    }


}

