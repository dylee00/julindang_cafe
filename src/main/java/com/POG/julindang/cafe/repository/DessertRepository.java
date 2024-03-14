package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    @Query("select b.dessertName from Dessert b where b.cafeName = :cafeName and b.deleted=false group by b.dessertName")
    List<Dessert> findBreadNamesByCafeName(String cafeName);

    List<Dessert> findBreadByCafeNameAndDessertName(@Param("cafeName") String cafeName, @Param("dessertName") String dessertName);
}
