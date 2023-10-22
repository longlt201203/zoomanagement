package com.swp.ZooManagement.apis.areas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreasRepository extends JpaRepository<Area, Integer> {
    Optional<Area> findByCode(String code);
}
