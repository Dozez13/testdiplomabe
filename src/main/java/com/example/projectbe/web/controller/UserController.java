package com.example.projectbe.web.controller;

import com.example.projectbe.core.dto.UserBasicDto;
import com.example.projectbe.core.service.UserService;
import com.example.projectbe.web.security.model.UserAuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping(value = "/basicCredentials")
    @PreAuthorize("authentication.principal.role == 'BUYER' OR authentication.principal.role == 'ADMIN'")
    public UserBasicDto getUserBasicDto() {
        return getFirstLastName();
    }

    private UserBasicDto getFirstLastName() {
        UserAuthenticationInfo authenticationInfo = (UserAuthenticationInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getByEmail(authenticationInfo.getEmail());
    }
}
