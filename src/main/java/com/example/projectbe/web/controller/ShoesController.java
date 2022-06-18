package com.example.projectbe.web.controller;


import com.example.projectbe.core.dto.ShoesInfoDto;
import com.example.projectbe.core.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure/shoes")
@RequiredArgsConstructor
public class ShoesController {

    private final ShoesService shoesService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public ShoesInfoDto getShoesInfo(@PathVariable("id") Long id) {
        return shoesService.getShoesInfo(id);
    }
}
