package com.nhom3.zoomanagement.cages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CagesRepository extends JpaRepository<Cage, Integer> {
    boolean existsByCode(String code);
}
