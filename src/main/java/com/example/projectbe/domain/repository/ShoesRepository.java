package com.example.projectbe.domain.repository;

import com.example.projectbe.domain.entity.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoesRepository extends JpaRepository<Shoes,Long>, JpaSpecificationExecutor<Shoes> {

    @Query("SELECT s FROM Shoes s"
            +" WHERE s.shoesModel.id = :shoesModelId"
    )
    List<Shoes> findAllByShoesModelId(@Param("shoesModelId") Long shoesModelId);
}
