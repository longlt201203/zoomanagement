package com.swp.ZooManagement.apis.animals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalsRepository extends JpaRepository<Animal, Integer> {
}
