package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.apis.cages.Cage;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface MealRecordsRepository extends JpaRepository<MealRecord, Integer> {
    @Query("SELECT mr " +
            "FROM MealRecord mr " +
            "WHERE mr.cageMeal.cage.id = :cageId AND (CAST(:date AS DATE) <= mr.createdAt AND mr.createdAt <= DATEADD(DAY, 1, CAST(:date AS DATE)))")
    List<MealRecord> findAllMealRecordsOfCage(@Param("cageId") Integer cageId, @Param("date") Instant date);
}
