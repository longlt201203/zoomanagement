package com.swp.ZooManagement.apis.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AreasRepository extends JpaRepository<Area, Integer> {
    Optional<Area> findByCode(String code);

    @Query("SELECT a.id AS id, a.code AS code, a.name AS name, a.location AS location, " +
            "COUNT(c) AS cageCount, COALESCE(SUM(ac.subAnimalCount), 0) AS animalCount " +
            "FROM Area a " +
            "LEFT JOIN a.cages c " +
            "LEFT JOIN (SELECT ca.id AS cageId, COUNT(an.id) AS subAnimalCount " +
            "           FROM Cage ca " +
            "           LEFT JOIN ca.animals an " +
            "           GROUP BY ca.id) ac " +
            "ON c.id = ac.cageId " +
            "GROUP BY a.id, a.code, a.name, a.location")
    List<GetAreasWithStatisticsResult> findAllWithStatistics();
}
