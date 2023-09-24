package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnimalRepository extends JpaRepository<Test, String> {
}
