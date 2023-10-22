package com.swp.ZooManagement.apis.animalspecies;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalSpeciesRepository extends JpaRepository<AnimalSpecies, Integer> {
    Optional<AnimalSpecies> findByName(String name);
}
