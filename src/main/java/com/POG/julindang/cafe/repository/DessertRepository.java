package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    @Query("select d.dessertName from Dessert d where d.cafeName = :cafeName and d.deleted=false group by d.dessertName")
    List<Dessert> findBreadNamesByCafeName(String cafeName);

    @Query("select d from Dessert d where d.cafeName = :cafeName and d.dessertName = :dessertName and d.deleted=false")
    List<Dessert> findBreadByCafeNameAndDessertName(@Param("cafeName") String cafeName, @Param("dessertName") String dessertName);
}
