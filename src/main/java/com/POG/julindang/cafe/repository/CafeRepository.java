package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository <Cafe, Long>{
    List<Cafe> findByBeverageName(@Param("beverageName") String beverageName);

    List<Cafe> findByCafeName(@Param("cafeName") String cafeName);
    @Query("select c.beverageName from Cafe c where c.cafeName=:cafeName and c.deleted=false group by c.beverageName")
    List<String> findDistinctByCafeName(@Param("cafeName") String cafeName);
    List<Cafe> findByCafeNameAndBeverageName(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName);
    @Query("SELECT distinct c.cafeName from Cafe c where c.deleted=false")
    List<String> findDistinctCafeName();
}
