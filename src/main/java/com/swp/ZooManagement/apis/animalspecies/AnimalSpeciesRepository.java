package com.swp.ZooManagement.apis.animalspecies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalSpeciesRepository extends JpaRepository<AnimalSpecies, Integer> {
    Optional<AnimalSpecies> findByName(String name);

    @Query(value = "SELECT speciesId, a.name AS speciesName, totalAnimal " +
            "FROM animal_species a " +
            "JOIN (" +
            "SELECT species_id AS speciesId, COUNT(*) AS totalAnimal " +
            "FROM animal " +
            "GROUP BY species_id " +
            ") b " +
            "ON a.id = speciesId", nativeQuery = true)
    List<GetAnimalSpeciesStatisticsResult> getAnimalSpeciesStatistics();
}
