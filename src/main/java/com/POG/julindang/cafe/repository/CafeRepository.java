package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
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
    public List<BeverageNameVo> findByCafeName(@Param("cafeName") String cafeName, @Param("userEmail") String userEmail);

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
    public List<BeverageNameVo> findByBeverageName(@Param("beverageName") String beverageName, @Param("userEmail") String userEmail);

    @Query("select c from Cafe c where c.cafeName=:cafeName and c.beverageName=:beverageName and c.deleted=false")
    public List<Cafe> findByCafeNameAndBeverageName(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName);

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
    public List<BeverageNameVo> findAllBeverageNames(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("userEmail") String userEmail);

    @Query(nativeQuery = true, value = "SELECT *" +
            "FROM dessert " +
            "ORDER BY sugar DESC " +
            "LIMIT 10")
    public List<CommonResponseDto> getMaxSugarDessert();

    /**
     * 음료 중 당이 제일 높은 10개 가져오기 => 중복 제거 + 최소 최대 구하기 내림차순
     */
    @Query(nativeQuery = true, value="WITH RankedBeverages AS (" +
            "  SELECT " +
            "    c.cafe_id, " +
            "    c.beverage_name AS name, " +
            "    c.cafe_name, " +
            "    sugars.min_sugar, " +
            "    sugars.max_sugar, " +
            "    c.sugar, " +
            "    ROW_NUMBER() OVER (PARTITION BY c.beverage_name ORDER BY c.sugar DESC) AS rn " +
            "  FROM " +
            "    cafe c " +
            "  JOIN (" +
            "    SELECT" +
            "        beverage_name, " +
            "        MIN(sugar) AS min_sugar, " +
            "        MAX(sugar) AS max_sugar " +
            "    FROM " +
            "        cafe " +
            "    GROUP BY " +
            "        beverage_name " +
            "  ) sugars ON c.beverage_name = sugars.beverage_name " +
            "), " +
            "TopBeverages AS ( " +
            "  SELECT " +
            "    cafe_id, " +
            "    name, " +
            "    cafe_name, " +
            "    min_sugar, " +
            "    max_sugar " +
            "  FROM " +
            "    RankedBeverages " +
            "  WHERE" +
            "    rn = 1 " +
            "  ORDER BY " +
            "    sugar DESC " +
            "  LIMIT 10 " +
            ") " +
            "SELECT " +
            "  tb.cafe_id as id, " +
            "  tb.name as name," +
            "  tb.cafe_name as cafeName, " +
            "  COALESCE(bi.url, '') AS img, " +
            "  tb.min_sugar as minSugar, " +
            "  tb.max_sugar as maxSugar, " +
            "  CASE " +
            "    WHEN bb.beverage_name IS NOT NULL and bb.member_id = :memberId THEN 1 " +
            "    ELSE 0 " +
            "  END AS isLiked " +
            "FROM " +
            "  TopBeverages tb " +
            "LEFT JOIN " +
            "  beverage_image bi ON tb.name = bi.beverage_name AND tb.cafe_name = bi.cafe_name " +
            "LEFT JOIN " +
            "  beverage_bookmark bb ON tb.name = bb.beverage_name AND tb.cafe_name = bb.cafe_name " +
            "ORDER BY " +
            "  tb.min_sugar DESC")
    public List<CommonResponseDto> getMaxSugarBeverageDesc(@Param("memberId") Long memberId);

    /**
     * 음료 중 당이 제일 높은 10개 가져오기 => 중복 제거 + 최소 최대 구하기 오름차순
     */
    @Query(nativeQuery = true, value="WITH RankedBeverages AS (" +
            "  SELECT " +
            "    c.cafe_id, " +
            "    c.beverage_name AS name, " +
            "    c.cafe_name, " +
            "    sugars.min_sugar, " +
            "    sugars.max_sugar, " +
            "    c.sugar, " +
            "    ROW_NUMBER() OVER (PARTITION BY c.beverage_name ORDER BY c.sugar DESC) AS rn " +
            "  FROM " +
            "    cafe c " +
            "  JOIN (" +
            "    SELECT" +
            "        beverage_name, " +
            "        MIN(sugar) AS min_sugar, " +
            "        MAX(sugar) AS max_sugar " +
            "    FROM " +
            "        cafe " +
            "    GROUP BY " +
            "        beverage_name " +
            "  ) sugars ON c.beverage_name = sugars.beverage_name " +
            "), " +
            "TopBeverages AS ( " +
            "  SELECT " +
            "    cafe_id, " +
            "    name, " +
            "    cafe_name, " +
            "    min_sugar, " +
            "    max_sugar " +
            "  FROM " +
            "    RankedBeverages " +
            "  WHERE" +
            "    rn = 1 " +
            "  ORDER BY " +
            "    sugar " +
            "  LIMIT 10 " +
            ") " +
            "SELECT " +
            "  tb.cafe_id as id, " +
            "  tb.name as name," +
            "  tb.cafe_name as cafeName, " +
            "  COALESCE(bi.url, '') AS img, " +
            "  tb.min_sugar as minSugar, " +
            "  tb.max_sugar as maxSugar, " +
            "  CASE " +
            "    WHEN bb.beverage_name IS NOT NULL THEN 1 " +
            "    ELSE 0 " +
            "  END AS isLiked " +
            "FROM " +
            "  TopBeverages tb " +
            "LEFT JOIN " +
            "  beverage_image bi ON tb.name = bi.beverage_name AND tb.cafe_name = bi.cafe_name " +
            "LEFT JOIN " +
            "  beverage_bookmark bb ON tb.name = bb.beverage_name AND tb.cafe_name = bb.cafe_name " +
            "ORDER BY " +
            "  tb.min_sugar")
    public List<CommonResponseDto> getMaxSugarBeverageAsc();
}
