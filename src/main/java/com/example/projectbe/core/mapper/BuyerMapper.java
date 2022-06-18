package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.BuyerRegistrationDto;
import com.example.projectbe.domain.entity.Buyer;
import com.example.projectbe.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyerMapper {

    private final PasswordEncoder passwordEncoder;

    public Buyer fromBuyerRegistrationDto(BuyerRegistrationDto buyerRegistrationDto) {
        Buyer buyer = new Buyer();
        buyer.setEmail(buyerRegistrationDto.getEmail());
        buyer.setFirstName(buyerRegistrationDto.getFirstName());
        buyer.setLastName(buyerRegistrationDto.getLastName());
        buyer.setPassword(passwordEncoder.encode(buyerRegistrationDto.getPassword()));
        buyer.setBirthDate(buyerRegistrationDto.getBirthDate());
        buyer.setUserRole(UserRole.BUYER);
        return buyer;
    }
}
