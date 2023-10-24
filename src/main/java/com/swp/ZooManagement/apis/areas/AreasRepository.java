package com.swp.ZooManagement.apis.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AreasRepository extends JpaRepository<Area, Integer> {
    Optional<Area> findByCode(String code);

    @Query(nativeQuery = true, value = "SELECT area.id as id, area.code as code, area.name as name, area.location as location, cageCount, animalCount\n" +
            "FROM area\n" +
            "LEFT JOIN (\n" +
            "\tSELECT cage.area_id AS t1_cage_area_id, COUNT(cage.area_id) as cageCount \n" +
            "\tFROM cage \n" +
            "\tGROUP BY cage.area_id\n" +
            ") t1\n" +
            "ON area.id = t1_cage_area_id\n" +
            "LEFT JOIN (\n" +
            "\tSELECT cage.area_id as t2_cage_area_id, animalCount\n" +
            "\tFROM cage\n" +
            "\tLEFT JOIN (\n" +
            "\t\tSELECT animal.cage_id AS u1_animal_cage_id, COUNT(animal.cage_id) as animalCount \n" +
            "\t\tFROM animal \n" +
            "\t\tGROUP BY animal.cage_id  \n" +
            "\t) u1\n" +
            "\tON cage.id = u1_animal_cage_id\n" +
            ") t2\n" +
            "ON area.id = t2_cage_area_id;")
    List<GetAreasWithStatisticsResult> findAllWithStatistics();
}
