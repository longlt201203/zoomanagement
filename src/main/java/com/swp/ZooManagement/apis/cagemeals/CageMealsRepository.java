package com.swp.ZooManagement.apis.cagemeals;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface CageMealsRepository extends JpaRepository<CageMeal, Integer> {
    Optional<CageMeal> findByCageIdAndTime(Integer cageId, Instant time);
}
