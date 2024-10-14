package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.BeverageBookmark;
import com.POG.julindang.cafe.domain.CafeBookmark;
import com.POG.julindang.cafe.domain.DessertBookmark;
import com.POG.julindang.cafe.vo.DessertBookmarkVo;
import com.POG.julindang.cafe.vo.FreeConsumeBookmarkVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DessertBookmarkRepository extends JpaRepository<DessertBookmark, Long> {

    @Query("SELECT d FROM DessertBookmark d WHERE d.userEmail =:userEmail AND d.cafeName =:cafeName AND d.dessertName =:dessertName ORDER BY d.createdAt DESC LIMIT 1 ")
    Optional<DessertBookmark> findByUserEmailAndCafeNameAndDessertName(@Param("userEmail") String userEmail, @Param("cafeName") String cafeName, @Param("dessertName") String dessertName);

    @Query("SELECT d FROM DessertBookmark d WHERE d.memberId =:memberId AND d.dessertId =:dessertId ORDER BY d.createdAt DESC LIMIT 1 ")
    Optional<DessertBookmark> findBymemberIdAndDessertId(@Param("memberId") Long memberId, @Param("dessertId") Long dessertId);

    Boolean existsByMemberIdAndDessertIdAndDeleted(@Param("memberId") Long memberId, @Param("dessertId") Long dessertId, @Param("deleted") Boolean deleted);

    @Query(nativeQuery = true, value = " SELECT d.dessert_name dessertName, d.cafe_name cafeName, d.created_at createdAt, ds.calorie, ds.sugar, d.dessert_id dessertId " +
            "FROM dessert_bookmark d " +
            "JOIN dessert ds on ds.dessert_id = d.dessert_id " +
            "WHERE member_id = :memberId AND d.deleted = 0 ")
    List<DessertBookmarkVo> findDessertBookmarkByMemberId(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value = " SELECT p.product_id productId,p.name , p.created_at createdAt, p.calorie, p.sugar, p.type, p.size  " +
            "FROM free_product p " +
            "WHERE p.bookmark = true AND p.member_id = :memberId AND p.type = 0 ")
    List<FreeConsumeBookmarkVo> findFreeConsumeDessertBookmarkByMemberId(@Param("memberId") Long memberId);

}
