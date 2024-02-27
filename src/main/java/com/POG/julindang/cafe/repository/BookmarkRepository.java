package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByUserEmail(@Param("userEmail") String userEmail);
    Bookmark findByCafeNameAndBeverageNameAndUserEmail(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName, @Param("userEmail") String userEmail);
}
