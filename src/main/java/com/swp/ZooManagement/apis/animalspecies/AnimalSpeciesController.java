package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal-species")
public class AnimalSpeciesController extends AbstractZooManagementController<AnimalSpecies, Integer, CreateAnimalSpeciesDto, UpdateAnimalSpeciesDto, FilterAnimalSpeciesDto, AnimalSpeciesResponseDto> {
}
