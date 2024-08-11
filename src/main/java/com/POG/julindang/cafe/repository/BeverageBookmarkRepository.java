package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.BeverageBookmark;
import com.POG.julindang.cafe.domain.CafeBookmark;
import com.POG.julindang.cafe.vo.BeverageBookmarkVo;
import com.POG.julindang.cafe.vo.DessertBookmarkVo;
import com.POG.julindang.cafe.vo.FreeConsumeBookmarkVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeverageBookmarkRepository extends JpaRepository<BeverageBookmark, Long> {

    Optional<BeverageBookmark> findByUserEmailAndCafeNameAndBeverageName(@Param("userEmail") String userEmail, @Param("cafeName") String cafeName, @Param("beverageName") String beverageName);

    @Query("SELECT b FROM BeverageBookmark b WHERE b.memberId =:memberId AND b.beverageId =:beverageId ORDER BY b.createdAt DESC LIMIT 1 ")
    Optional<BeverageBookmark> findBymemberIdAndBeverageId(@Param("memberId") Long memberId, @Param("beverageId") Long beverageId);

    Boolean existsByMemberIdAndBeverageIdAndDeleted(@Param("memberId") Long memberId, @Param("beverageId") Long beverageId, @Param("deleted") Boolean deleted);
    @Query(nativeQuery = true, value =  " SELECT p.product_id productId,p.name , p.created_at createdAt, p.calorie, p.sugar, p.type " +
            "FROM free_product p " +
            "WHERE p.bookmark = true AND p.member_id = :memberId AND p.type = 1 ")
    List<FreeConsumeBookmarkVo> findFreeConsumeBeverageBookmarkByMemberId(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value =  " SELECT p.product_id productId,p.name , p.created_at createdAt, p.calorie, p.sugar, p.type " +
            "FROM free_product p " +
            "WHERE p.bookmark = true AND p.member_id = :memberId ")
    List<FreeConsumeBookmarkVo> findFreeConsumeBookmarkByMemberId(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value = " SELECT " +
            "b.beverage_name AS beverageName, " +
            "MIN(b.cafe_name) AS cafeName, " +
            "MIN(b.created_at) AS createdAt, " +
            "MIN(c.calorie) AS calorie, " +
            "MIN(c.sugar) AS sugar, " +
            "MIN(b.beverage_id) AS beverageId " +
            "FROM beverage_bookmark b " +
            "LEFT JOIN cafe c ON c.beverage_name = b.beverage_name " +
            "WHERE b.member_id = :memberId AND b.deleted = 0 " +
            "GROUP BY " +
            "b.beverage_name ")
    List<BeverageBookmarkVo> findBeverageBookmarkByMemberId(@Param("memberId") Long memberId);
}

