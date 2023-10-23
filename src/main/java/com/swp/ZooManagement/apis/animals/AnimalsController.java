package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalsController extends AbstractZooManagementController<Animal, Integer, CreateAnimalDto, UpdateAnimalDto, FilterAnimalDto, AnimalResponseDto> {
}
