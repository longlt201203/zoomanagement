package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMealRepository extends JpaRepository<Test, String> {
}
