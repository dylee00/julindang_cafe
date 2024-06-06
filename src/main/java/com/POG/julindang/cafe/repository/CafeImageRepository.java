package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.CafeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeImageRepository extends JpaRepository<CafeImage, Long> {

    Optional<CafeImage> findByCafeName(@Param("cafeName") String cafeName);
}
