package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.CafeBookmark;
import com.POG.julindang.cafe.domain.DessertBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DessertBookmarkRepository extends JpaRepository<DessertBookmark, Long> {

    Optional<DessertBookmark> findByUserEmailAndCafeNameAndDessertName(@Param("userEmail") String userEmail, @Param("cafeName") String cafeName, @Param("dessertName") String dessertName);
}
