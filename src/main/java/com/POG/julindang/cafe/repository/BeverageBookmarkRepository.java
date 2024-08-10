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

    Optional<BeverageBookmark> findBymemberIdAndBeverageId(@Param("memberId") Long memberId, @Param("beverageId") Long beverageId);

    Boolean existsByMemberIdAndBeverageIdAndDeleted(@Param("memberId") Long memberId, @Param("beverageId") Long beverageId, @Param("deleted") Boolean deleted);
    @Query(nativeQuery = true, value =  " SELECT f.consume_id consumeId ,p.product_id productId,f.name , p.created_at createdAt, f.calorie, f.sugar, f.type " +
            "FROM free_consume f " +
            "LEFT JOIN free_product p on p.product_id = f.product_id " +
            "WHERE p.bookmark = true AND p.member_id = :memberId AND f.deleted = 0 ")
    List<FreeConsumeBookmarkVo> findFreeConsumeBeverageBookmarkByMemberId(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value =  " SELECT f.consume_id consumeId ,p.product_id productId,f.name , p.created_at createdAt, f.calorie, f.sugar, f.type " +
            "FROM free_consume f " +
            "LEFT JOIN free_product p on p.product_id = f.product_id " +
            "WHERE p.bookmark = true AND p.member_id = :memberId AND f.deleted = 0 AND f.type = 1 ")
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

