package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
    void deleteById(@Param("toppingId") Long id);
    Optional<List<Topping>> findByCafeNameAndBeverageName(@Param ("cafeName") String cafeName, @Param("beverageName") String beverageName);
}
