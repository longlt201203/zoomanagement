package com.swp.ZooManagement.apis.meals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealsRepository extends JpaRepository<Meal, Integer> {
}
