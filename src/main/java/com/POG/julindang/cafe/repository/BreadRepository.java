package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Bread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BreadRepository extends JpaRepository<Bread, Long> {
    @Query("select b.breadName from Bread b where b.cafeName = :cafeName and b.deleted=false group by b.breadName")
    List<Bread> findBreadNamesByCafeName(String cafeName);

    List<Bread> findBreadByCafeNameAndBreadName(@Param("cafeName") String cafeName, @Param("breadName") String breadName);
}
