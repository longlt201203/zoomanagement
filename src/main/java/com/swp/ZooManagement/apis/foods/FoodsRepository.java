package com.swp.ZooManagement.apis.foods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodsRepository extends JpaRepository<Food, Integer> {
    Optional<Food> findByName(String name);
}
