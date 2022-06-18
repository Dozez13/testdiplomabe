package com.example.projectbe.web.controller;

import com.example.projectbe.core.dto.BuyerRegistrationDto;
import com.example.projectbe.core.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserManagementController {

    private final BuyerService buyerService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBuyer(@RequestBody BuyerRegistrationDto buyerRegistrationDto) {
        buyerService.createBuyer(buyerRegistrationDto);
    }
}
