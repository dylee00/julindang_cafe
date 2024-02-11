package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
    void deleteById(@Param("toppingId") Long id);
}
