package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.CafeImage;
import com.POG.julindang.cafe.dto.response.cafe.CafeLikeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeImageRepository extends JpaRepository<CafeImage, Long> {
    @Query(nativeQuery = true, value = "select ci.cafe_name cafeName, ci.url from cafe_image ci where ci.cafe_name =:cafeName ")
    List<CafeResponseDto> getCafeName(@Param("cafeName") String cafeName);

    @Query(nativeQuery = true, value = "SELECT " +
            "ci.cafe_name cafeName, " +
            "ci.url, " +
            "CASE " +
            "WHEN cb.cafe_name IS NOT NULL THEN TRUE " +
            "ELSE FALSE " +
            "END AS isLiked " +
            "FROM " +
            "cafe_image ci " +
            "LEFT JOIN " +
            "cafe_bookmark cb " +
            "ON " +
            "ci.cafe_name = cb.cafe_name " +
            "order by cafeName")
    List<CafeLikeResponseDto> getAllCafeImages();

    @Query(nativeQuery = true, value = "SELECT " +
            "ci.cafe_name cafeName, " +
            "ci.url, " +
            "CASE " +
            "WHEN cb.cafe_name IS NOT NULL THEN TRUE " +
            "ELSE FALSE " +
            "END AS isLiked " +
            "FROM " +
            "cafe_image ci " +
            "LEFT JOIN " +
            "cafe_bookmark cb " +
            "ON " +
            "ci.cafe_name = cb.cafe_name " +
            "WHERE cb.user_email = :userEmail " + // 특정 사용자의 북마크만 필터링
            "ORDER BY cafeName")
    List<CafeLikeResponseDto> getLikedCafeImages(@Param("userEmail") String userEmail);
}
