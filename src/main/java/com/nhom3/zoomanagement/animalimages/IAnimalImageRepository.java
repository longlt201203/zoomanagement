package com.nhom3.zoomanagement.animalimages;

import com.nhom3.zoomanagement.tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnimalImageRepository extends JpaRepository<Test, String> {
}
