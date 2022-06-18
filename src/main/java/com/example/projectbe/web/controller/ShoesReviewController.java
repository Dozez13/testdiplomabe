package com.example.projectbe.web.controller;


import com.example.projectbe.core.dto.ShoesReviewCreateDto;
import com.example.projectbe.core.dto.ShoesReviewDto;
import com.example.projectbe.core.dto.UserBasicDto;
import com.example.projectbe.core.dto.generic.ListResponseDto;
import com.example.projectbe.core.service.ShoesReviewService;
import com.example.projectbe.core.service.UserService;
import com.example.projectbe.domain.entity.User;
import com.example.projectbe.web.security.model.UserAuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/secure/shoes-reviews")
@RequiredArgsConstructor
public class ShoesReviewController {

    private final ShoesReviewService shoesReviewService;

    private final UserService userService;

    @GetMapping(value = "/{shoesId}")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ListResponseDto<ShoesReviewDto> getShoesReview(@PathVariable("shoesId") Long shoesId) {
        ListResponseDto<ShoesReviewDto> shoesReviewDtoListResponseDto = new ListResponseDto<>();
        shoesReviewDtoListResponseDto.setElements(shoesReviewService.getShoesReviewByShoesId(shoesId));
        return shoesReviewDtoListResponseDto;
    }

    @PostMapping
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    @ResponseStatus(HttpStatus.CREATED)
    public void postReview(@RequestBody ShoesReviewCreateDto shoesReviewCreateDto) {
        shoesReviewService.createReview(shoesReviewCreateDto, getUser());
    }

    private User getUser() {
        UserAuthenticationInfo authenticationInfo = (UserAuthenticationInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByEmail(authenticationInfo.getEmail());
    }
}
