package com.nhom3.zoomanagement.areas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreasRepository extends JpaRepository<Area, Integer> {
    boolean existsByCode(String code);
}
