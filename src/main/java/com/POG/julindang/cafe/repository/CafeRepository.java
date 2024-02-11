package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository <Cafe, Long>{

    List<Cafe> findByBeverageName(@Param("beverageName") String beverageName);
    List<Cafe> findByCafeName(@Param("cafeName") String cafeName);
    Optional<Cafe> findByCafeNameAndBeverageName(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName);
}
