package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {
}
