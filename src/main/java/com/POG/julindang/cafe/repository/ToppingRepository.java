package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
    List<Topping> findByCafeNameAndBeverageName(@Param ("cafeName") String cafeName, @Param("beverageName") String beverageName);
}
