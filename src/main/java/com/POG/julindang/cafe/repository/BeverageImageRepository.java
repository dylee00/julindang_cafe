package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.BeverageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeverageImageRepository extends JpaRepository<BeverageImage, Long> {
    List<BeverageImage> findByCafeName(@Param("cafeName") String cafeName);
}
