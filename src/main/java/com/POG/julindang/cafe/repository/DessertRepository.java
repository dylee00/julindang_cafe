package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Dessert;
import com.POG.julindang.cafe.vo.DessertNameVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
    // 디저트 정보 전체 불러오기
    // 디저트 디테일 불러오기
    // 카페 이름에 따른 디저트 정보 불러오기
    // 디저트 이름에 따른 디저트 정보 불러오기

    @Query(nativeQuery = true, value = "select d.dessert_name as dessertName, " +
            "MIN(d.sugar) as minSugar, " +
            "MAX(d.sugar) as maxSugar, " +
            "MIN(i.url) as url, " +
            "d.cafe_name as cafeName, " +
            "case when b.user_email is not null then true else false end as bookmarked " +
            "from dessert as d " +
            "left join dessert_image as i " +
            "on " +
            "i.dessert_name=d.dessert_name " +
            " left join dessert_bookmark as b " +
            "on d.dessert_name = (select b.dessert_name where b.user_email = :userEmail and b.deleted=false) " +
            "where " +
            "d.deleted=false " +
            "group by " +
            "b.user_email, d.dessert_name, d.cafe_name " +
            "order by bookmarked is null, bookmarked desc " +
            "limit :limit offset :offset")
    List<DessertNameVo> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("userEmail") String userEmail);

    @Query("select d from Dessert d where d.cafeName=:cafeName and d.dessertName=:dessertName and d.deleted=false")
    List<Dessert> findByCafeNameAndDessertName(@Param("cafeName") String cafeName, @Param("dessertName") String dessertName);

    @Query(nativeQuery = true, value = "select d.dessert_name as dessertName, " +
            "MIN(d.sugar) as minSugar, " +
            "MAX(d.sugar) as maxSugar, " +
            "MIN(i.url) as url, " +
            "d.cafe_name as cafeName, " +
            "case when b.user_email is not null then true else false end as bookmarked " +
            "from dessert as d " +
            "left join dessert_image as i " +
            "on " +
            "i.dessert_name=d.dessert_name " +
            " left join dessert_bookmark as b " +
            "on d.dessert_name = (select b.dessert_name where b.user_email = :userEmail and b.deleted=false) " +
            "where " +
            "d.deleted=false and d.cafe_name=:cafeName" +
            "group by " +
            "b.user_email, d.dessert_name, d.cafe_name " +
            "order by bookmarked is null, bookmarked desc " +
            "limit :limit offset :offset")
    List<DessertNameVo> findByCafeName(@Param("cafeName") String cafeName, @Param("userEmail") String userEmail);


    @Query(nativeQuery = true, value = "select d.dessert_name as dessertName, " +
            "MIN(d.sugar) as minSugar, " +
            "MAX(d.sugar) as maxSugar, " +
            "MIN(i.url) as url, " +
            "d.cafe_name as cafeName, " +
            "case when b.user_email is not null then true else false end as bookmarked " +
            "from dessert as d " +
            "left join dessert_image as i " +
            "on " +
            "i.dessert_name=d.dessert_name " +
            " left join dessert_bookmark as b " +
            "on d.dessert_name = (select b.dessert_name where b.user_email = :userEmail and b.deleted=false) " +
            "where " +
            "d.deleted=false and d.dessertName=:dessertName" +
            "group by " +
            "b.user_email, d.dessert_name, d.cafe_name " +
            "order by bookmarked is null, bookmarked desc " +
            "limit :limit offset :offset")
    List<DessertNameVo> findByDessertName(@Param("dessertName") String dessertName, @Param("userEmail") String userEmail);
}
