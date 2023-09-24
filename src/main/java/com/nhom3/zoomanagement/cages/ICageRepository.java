package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICageRepository extends JpaRepository<Test, String> {
}
