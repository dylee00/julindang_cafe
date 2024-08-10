package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.CafeBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeBookmarkRepository extends JpaRepository<CafeBookmark, Long> {
    @Query("select b from CafeBookmark b where b.userEmail=:userEmail and b.deleted=false")
    List<CafeBookmark> findAllByUserEmail(@Param("userEmail") String userEmail);

    // cafe 즐겨찾기 된거 뽑아오기
    @Query("select b.cafeName from CafeBookmark b where b.userEmail = :userEmail and b.deleted=false group by b.cafeName")
    List<String> findDistinctCafeNameByUserEmail(@Param("userEmail") String userEmail);


    Optional<CafeBookmark> findByUserEmailAndCafeName(@Param("userEmail") String userEmail, @Param("cafeName") String cafeName);

    Boolean existsByMemberIdAndCafeNameAndDeleted(@Param("memberId") Long memberId, @Param("cafeName") String cafeName, @Param("deleted") Boolean deleted);


    // 해당 cafe 안에 즐겨찾기 음식들 뽑아오기
//    @Query("select b.productName from CafeBookmark b where b.userEmail = :userEmail and b.cafeName= :cafeName and b.deleted=false group by b.productName")
//    List<String> findDistinctProductNameByUserEmailAndCafeName(@Param("userEmail") String userEmail,
//                                                                 @Param("cafeName") String cafeName);
//    @Query("select b from CafeBookmark b where b.userEmail = :userEmail and b.cafeName= :cafeName and b.productName=:productName and b.deleted=false")
//    Optional<CafeBookmark> findByCafeNameAndProductNameAndUserEmail(@Param("cafeName") String cafeName,
//                                                                    @Param("productName") String productName,
//                                                                    @Param("userEmail") String userEmail);
}

