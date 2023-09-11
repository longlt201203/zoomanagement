package com.nhom3.zoomanagement.tests;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repository
public interface ITestRepository extends JpaRepository<Test, String> {
    boolean existsByUniqueField(String uniqueField);
}
