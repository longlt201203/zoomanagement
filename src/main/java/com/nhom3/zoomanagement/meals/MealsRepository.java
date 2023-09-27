package com.nhom3.zoomanagement.meals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealsRepository extends JpaRepository<Meal, String> {
}
