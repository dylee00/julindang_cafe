package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Dessert;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.vo.BeverageDetailVo;
import com.POG.julindang.cafe.vo.DessertDetailVo;
import com.POG.julindang.cafe.vo.DessertNameVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DessertRepository extends JpaRepository<Dessert, Long> {

    @Query(nativeQuery = true, value = "select d.dessert_name as dessertName, " +
            "i.url AS url, " +
            "d.cafe_name as cafeName, " +
            "d.dessert_id as dessertId, " +
            "d.sugar as sugar, " +
            "CASE " +
            "  WHEN d.dessert_name IN (SELECT db.dessert_name FROM dessert_bookmark AS db WHERE db.member_id = :memberId) " +
            "  THEN 'true' " +
            "  ELSE 'false' " +
            "END AS bookmarked " +
            "from dessert as d " +
            "left join dessert_image as i ON i.dessert_name = d.dessert_name AND i.cafe_name = d.cafe_name " +
            "where d.deleted=false " +
            "order by d.dessert_name ")
    List<DessertNameVo> findAll(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value = "SELECT d.dessert_id dessertId, d.dessert_name dessertName, d.cafe_name cafeName, d.calorie, d.serve, d.sugar, i.url, " +
            "case when b.dessert_id is not null then 'true' else 'false' end as bookmarked " +
            "from dessert d " +
            "left join dessert_image i on i.dessert_name = d.dessert_name and i.cafe_name = d.cafe_name " +
            "left join dessert_bookmark b on b.dessert_id = d.dessert_id  and b.member_id = :memberId and b.deleted = false " +
            "where d.deleted=false and d.cafe_name =:cafeName and d.dessert_name =:dessertName ")
    List<DessertDetailVo> findByCafeNameAndDessertName(@Param("cafeName") String cafeName, @Param("dessertName") String dessertName, @Param("memberId")Long memberId);

    @Query(nativeQuery = true, value = "select d.dessert_name as dessertName, " +
            "i.url AS url, " +
            "d.cafe_name as cafeName, " +
            "d.dessert_id as dessertId, " +
            "d.sugar as sugar, " +
            "CASE " +
            "  WHEN d.dessert_name IN (SELECT db.dessert_name FROM dessert_bookmark AS db WHERE db.member_id = :memberId) " +
            "  THEN 'true' " +
            "  ELSE 'false' " +
            "END AS bookmarked " +
            "from dessert as d " +
            "left join dessert_image as i ON i.dessert_name = d.dessert_name AND i.cafe_name = d.cafe_name " +
            "where d.deleted = false and match(d.cafe_name) against(:cafeName IN BOOLEAN MODE) " +
            "order by d.dessert_name ")
    List<DessertNameVo> findByCafeName(@Param("cafeName") String cafeName, @Param("memberId")Long memberId);

    @Query(nativeQuery = true, value = "select d.dessert_name as dessertName, " +
            "i.url AS url, " +
            "d.cafe_name as cafeName, " +
            "d.dessert_id as dessertId, " +
            "d.sugar as sugar, " +
            "CASE " +
            "  WHEN d.dessert_name IN (SELECT db.dessert_name FROM dessert_bookmark AS db WHERE db.member_id = :memberId) " +
            "  THEN 'true' " +
            "  ELSE 'false' " +
            "END AS bookmarked " +
            "from dessert as d " +
            "left join dessert_image as i ON i.dessert_name = d.dessert_name AND i.cafe_name = d.cafe_name " +
            "where d.deleted = false and match(d.dessert_name) against(:dessertName IN BOOLEAN MODE) " +
            "order by d.dessert_name asc ")
    List<DessertNameVo> findByDessertName(@Param("dessertName") String dessertName, @Param("memberId")Long memberId);

    /**
     * 당류 높은 순 10개 가져오기
     * */
    @Query(nativeQuery = true, value = "WITH RankedDesserts AS ( " +
            "  SELECT " +
            "    d.dessert_id, " +
            "    d.dessert_name AS name, " +
            "    d.cafe_name, " +
            "    sugars.min_sugar, " +
            "    sugars.max_sugar, " +
            "    d.sugar " +
            "  FROM " +
            "    dessert d " +
            "  JOIN ( " +
            "    SELECT " +
            "        dessert_name, " +
            "        MIN(sugar) AS min_sugar, " +
            "        MAX(sugar) AS max_sugar " +
            "    FROM " +
            "        dessert " +
            "    GROUP BY " +
            "        dessert_name " +
            "  ) sugars ON d.dessert_name = sugars.dessert_name " +
            "), " +
            "TopDesserts AS ( " +
            "  SELECT " +
            "    dessert_id, " +
            "    name, " +
            "    cafe_name, " +
            "    min_sugar, " +
            "    max_sugar " +
            "  FROM " +
            "    RankedDesserts " +
            "  ORDER BY " +
            "    sugar DESC " +
            "  LIMIT 10 " +
            ") " +
            "SELECT " +
            "  td.dessert_id as id, " +
            "  td.name as name, " +
            "  td.cafe_name as cafeName, " +
            "  COALESCE(di.url, '') AS img, " +
            "  td.min_sugar as minSugar, " +
            "  td.max_sugar as maxSugar, " +
            "  CASE " +
            "    WHEN db.dessert_name IS NOT NULL THEN 1 " +
            "    ELSE 0 " +
            "  END AS isLiked " +
            "FROM " +
            "  TopDesserts td " +
            "LEFT JOIN " +
            "  dessert_image di ON td.name = di.dessert_name AND td.cafe_name = di.cafe_name " +
            "LEFT JOIN " +
            "  dessert_bookmark db ON td.name = db.dessert_name AND td.cafe_name = db.cafe_name " +
            "ORDER BY " +
            "  td.min_sugar DESC")
    public List<CommonResponseDto> getDessertListDesc();


    /**
     * 당류 높은 순 10개 가져오기
     * */
    @Query(nativeQuery = true, value = "WITH RankedDesserts AS ( " +
            "  SELECT " +
            "    d.dessert_id, " +
            "    d.dessert_name AS name, " +
            "    d.cafe_name, " +
            "    sugars.min_sugar, " +
            "    sugars.max_sugar, " +
            "    d.sugar " +
            "  FROM " +
            "    dessert d " +
            "  JOIN ( " +
            "    SELECT " +
            "        dessert_name, " +
            "        MIN(sugar) AS min_sugar, " +
            "        MAX(sugar) AS max_sugar " +
            "    FROM " +
            "        dessert " +
            "    GROUP BY " +
            "        dessert_name " +
            "  ) sugars ON d.dessert_name = sugars.dessert_name " +
            "), " +
            "TopDesserts AS ( " +
            "  SELECT " +
            "    dessert_id, " +
            "    name, " +
            "    cafe_name, " +
            "    min_sugar, " +
            "    max_sugar " +
            "  FROM " +
            "    RankedDesserts " +
            "  ORDER BY " +
            "    sugar " +
            "  LIMIT 10 " +
            ") " +
            "SELECT " +
            "  td.dessert_id as id, " +
            "  td.name as name, " +
            "  td.cafe_name as cafeName, " +
            "  COALESCE(di.url, '') AS img, " +
            "  td.min_sugar as minSugar, " +
            "  td.max_sugar as maxSugar, " +
            "  CASE " +
            "    WHEN db.dessert_name IS NOT NULL THEN 1 " +
            "    ELSE 0 " +
            "  END AS isLiked " +
            "FROM " +
            "  TopDesserts td " +
            "LEFT JOIN " +
            "  dessert_image di ON td.name = di.dessert_name AND td.cafe_name = di.cafe_name " +
            "LEFT JOIN " +
            "  dessert_bookmark db ON td.name = db.dessert_name AND td.cafe_name = db.cafe_name " +
            "ORDER BY " +
            "  td.min_sugar ")
    public List<CommonResponseDto> findDessertListAsc();

//    @Query(nativeQuery = true,value = "select dessert_id dessertId, dessert_name dessertName, cafe_name cafeName, calorie, serve,sugar " +
//            "from dessert where deleted=false and dessert_id = :dessertId ")
//    public List<DessertDetailVo> getDessertDetails(@Param("dessertId")Long dessertId);

    @Query(nativeQuery = true, value = "SELECT d.dessert_id dessertId, d.dessert_name dessertName, d.cafe_name cafeName, d.calorie, d.serve, d.sugar, i.url, " +
            "case when b.dessert_id is not null then 'true' else 'false' end as bookmarked " +
            "from dessert d " +
            "left join dessert_image i on i.dessert_name = d.dessert_name and i.cafe_name = d.cafe_name " +
            "left join dessert_bookmark b on b.dessert_id = d.dessert_id  and b.member_id = :memberId and b.deleted = false " +
            "where d.deleted=false and d.dessert_id = :dessertId")
    public List<DessertDetailVo> getDessertDetails(@Param("dessertId")Long dessertId, @Param("memberId")Long memberId);
}
