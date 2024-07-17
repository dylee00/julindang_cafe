package com.POG.julindang.cafe.repository;

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

    Optional<DessertBookmark> findByUserEmailAndCafeNameAndDessertName(@Param("userEmail") String userEmail, @Param("cafeName") String cafeName, @Param("dessertName") String dessertName);

    @Query(nativeQuery = true, value = " SELECT d.dessert_name dessertName, d.cafe_name cafeName, d.created_at createdAt, ds.calorie, ds.sugar, ds.dessert_id dessertId " +
            "FROM dessert_bookmark d " +
            "LEFT JOIN dessert ds on ds.dessert_name = d.dessert_name " +
            "WHERE member_id = :memberId AND d.deleted = 0 ")
    List<DessertBookmarkVo> findDessertBookmarkByMemberId(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value = " SELECT f.consume_id consumeId ,f.name , b.created_at createdAt, f.calorie, f.sugar " +
            "FROM free_consume f " +
            "LEFT JOIN free_consume_bookmark b on b.name = f.name " +
            "WHERE b.member_id = :memberId AND b.deleted = 0 AND f.type = 0 ")
    List<FreeConsumeBookmarkVo> findFreeConsumeDessertBookmarkByMemberId(@Param("memberId") Long memberId);

}
