package com.example.projectbe.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyerRegistrationDto {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private LocalDate birthDate;


}
