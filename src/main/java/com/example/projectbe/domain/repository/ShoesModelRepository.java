package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.ShoesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoesModelRepository extends JpaRepository<ShoesModel, Long>, JpaSpecificationExecutor<ShoesModel> {


    @Query("SELECT sm FROM ShoesModel sm"
            + " LEFT JOIN FETCH sm.shoes s"
            + " WHERE s.id = :shoesId ")
    ShoesModel findByShoesId(@Param("shoesId") Long shoesId);
}
