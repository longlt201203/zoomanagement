package com.nhom3.zoomanagement.repositories;

import com.nhom3.zoomanagement.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repository
public interface ITestRepository extends JpaRepository<Test, String> {
    boolean existsByUniqueField(String uniqueField);
}
