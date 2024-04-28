package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.response.cafe.BeverageNameGetterResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository <Cafe, Long>{
    @Query("select c from Cafe c where c.beverageName=:beverageName and c.deleted=false")
    List<Cafe> findByBeverageName(@Param("beverageName") String beverageName);
    @Query("select c from Cafe c where c.cafeName=:cafeName and c.deleted=false")
    List<Cafe> findByCafeName(@Param("cafeName") String cafeName);
    //max, min 함수 작성
    @Query("select c.beverageName from Cafe c where c.cafeName=:cafeName and c.deleted=false group by c.beverageName")
    List<String> findDistinctByCafeName(@Param("cafeName") String cafeName);

    // 쿼리 최적화
    @Query(nativeQuery = true, value = "select beverage_name as beverageName, MIN(sugar) as minSugar, MAX(sugar) as maxSugar from cafe as c where c.cafe_name=:cafeName and c.deleted=false group by beverage_name")
    List<BeverageNameGetterResponseDto> findDistinctByCafeNameUsingNative(@Param("cafeName") String cafeName);

    @Query("select c from Cafe c where c.cafeName=:cafeName and c.beverageName=:beverageName and c.deleted=false")
    List<Cafe> findByCafeNameAndBeverageName(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName);
    @Query("SELECT distinct c.cafeName from Cafe c where c.deleted=false")
    List<String> findDistinctCafeName();
}
