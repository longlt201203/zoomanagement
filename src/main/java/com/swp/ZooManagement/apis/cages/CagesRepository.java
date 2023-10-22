package com.swp.ZooManagement.apis.cages;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CagesRepository extends JpaRepository<Cage, Integer> {
    Optional<Cage> findByCode(String code);
}
