package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.BeverageBookmark;
import com.POG.julindang.cafe.domain.CafeBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeverageBookmarkRepository extends JpaRepository<BeverageBookmark, Long> {

    Optional<BeverageBookmark> findByUserEmailAndCafeNameAndBeverageName(@Param("userEmail") String userEmail, @Param("cafeName") String cafeName, @Param("beverageName") String beverageName);
}
