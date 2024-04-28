package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("select b from Bookmark b where b.userEmail=:userEmail and b.deleted=false")
    List<Bookmark> findAllByUserEmail(@Param("userEmail") String userEmail);

    // cafe 즐겨찾기 된거 뽑아오기
    @Query("select b.cafeName from Bookmark b where b.userEmail = :userEmail and b.deleted=false group by b.cafeName")
    List<String> findDistinctCafeNameByUserEmail(@Param("userEmail") String userEmail);
    // 해당 cafe 안에 즐겨찾기 음식들 뽑아오기
    @Query("select b.productName from Bookmark b where b.userEmail = :userEmail and b.cafeName= :cafeName and b.deleted=false group by b.productName")
    List<String> findDistinctProductNameByUserEmailAndCafeName(@Param("userEmail") String userEmail,
                                                                 @Param("cafeName") String cafeName);
    @Query("select b from Bookmark b where b.userEmail = :userEmail and b.cafeName= :cafeName and b.productName=:productName and b.deleted=false")
    Optional<Bookmark> findByCafeNameAndProductNameAndUserEmail(@Param("cafeName") String cafeName,
                                                                @Param("productName") String productName,
                                                                @Param("userEmail") String userEmail);
}

