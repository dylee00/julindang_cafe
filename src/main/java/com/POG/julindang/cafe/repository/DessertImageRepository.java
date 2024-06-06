package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.DessertImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertImageRepository extends JpaRepository<DessertImage ,Long> {
}
