package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.BuyerRegistrationDto;
import com.example.projectbe.core.mapper.BuyerMapper;
import com.example.projectbe.core.service.BuyerService;
import com.example.projectbe.domain.entity.Buyer;
import com.example.projectbe.domain.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    private final BuyerMapper buyerMapper;

    @Transactional
    @Override
    public void createBuyer(BuyerRegistrationDto buyerRegistrationDto) {
        Buyer buyer = buyerMapper.fromBuyerRegistrationDto(buyerRegistrationDto);
        buyerRepository.save(buyer);
    }
}
