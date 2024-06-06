package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.vo.BeverageNameVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository <Cafe, Long>{
    @Query(nativeQuery = true, value = "select c.beverage_name as beverageName, " +
            "MIN(c.sugar) as minSugar, " +
            "MAX(c.sugar) as maxSugar, " +
            "MIN(i.url) as url, " +
            "c.cafe_name as cafeName, " +
            "case when b.user_email is not null then true else false end as bookmarked " +
            "from cafe as c " +
            "left join beverage_image as i " +
            "on i.beverage_name = c.beverage_name " +
            "left join beverage_bookmark as b " +
            "on c.beverage_name = (select b.beverage_name where b.user_email = :userEmail) " +
            "where c.deleted = false and c.cafe_name=:cafeName " +
            "group by b.user_email, c.cafe_name, c.beverage_name " +
            "order by count is null, count desc " +
            "limit :limit offset :offset")
    List<BeverageNameVo> findByCafeName(@Param("cafeName") String cafeName, @Param("userEmail") String userEmail);
    @Query(nativeQuery = true, value = "select c.beverage_name as beverageName, " +
            "MIN(c.sugar) as minSugar, " +
            "MAX(c.sugar) as maxSugar, " +
            "MIN(i.url) as url, " +
            "c.cafe_name as cafeName, " +
            "case when b.user_email is not null then true else false end as bookmarked " +
            "from cafe as c " +
            "left join beverage_image as i " +
            "on i.beverage_name = c.beverage_name " +
            "left join beverage_bookmark as b " +
            "on c.beverage_name = (select b.beverage_name where b.user_email = :userEmail) " +
            "where c.deleted = false and c.beverage_name=:beverageName" +
            "group by b.user_email, c.cafe_name, c.beverage_name " +
            "order by count is null, count desc " +
            "limit :limit offset :offset")
    List<BeverageNameVo> findByBeverageName(@Param("beverageName") String beverageName, @Param("userEmail") String userEmail);
    @Query("select c from Cafe c where c.cafeName=:cafeName and c.beverageName=:beverageName and c.deleted=false")
    List<Cafe> findByCafeNameAndBeverageName(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName);

    @Query(nativeQuery = true,
            value = "select c.beverage_name as beverageName, " +
                    "MIN(c.sugar) as minSugar, " +
                    "MAX(c.sugar) as maxSugar, " +
                    "MIN(i.url) as url, " +
                    "c.cafe_name as cafeName, " +
                    "case when b.user_email is not null then true else false end as bookmarked " +
                    "from cafe as c " +
                    "left join beverage_image as i " +
                    "on i.beverage_name = c.beverage_name " +
                    "left join beverage_bookmark as b " +
                    "on c.beverage_name = (select b.beverage_name where b.user_email = :userEmail) " +
                    "where c.deleted = false " +
                    "group by b.user_email, c.cafe_name, c.beverage_name " +
                    "order by count is null, count desc " +
                    "limit :limit offset :offset")
    List<BeverageNameVo> findAllBeverageNames(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("userEmail") String userEmail);
}
