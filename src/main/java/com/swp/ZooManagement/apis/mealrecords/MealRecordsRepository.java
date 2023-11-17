package com.swp.ZooManagement.apis.mealrecords;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface MealRecordsRepository extends JpaRepository<MealRecord, Integer> {
    @Query("SELECT mr FROM MealRecord mr WHERE mr.meal.animal.id = :animalId AND " +
            "CAST(mr.createdAt AS DATE) = CAST(:date AS DATE)")
    List<MealRecord> findByAnimalAndCreateAt(@Param("animalId") Integer animalId, @Param("date") Instant date);
}
