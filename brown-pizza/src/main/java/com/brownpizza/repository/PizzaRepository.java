package com.brownpizza.repository;

import com.brownpizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    @Query("select p.basePrice from Pizza p where p.id = :id")
    BigDecimal findBasePriceById(@Param("id") Long id);

    @Query("select p.finalPrice from Pizza p where p.id = :id")
    BigDecimal findFinalPriceById(@Param("id") Long id);
}
