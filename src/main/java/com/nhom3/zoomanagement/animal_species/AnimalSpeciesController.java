package com.nhom3.zoomanagement.animal_species;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AnimalSpeciesController implements IAnimalSpeciesController{
    @Autowired
    AnimalSpeciesService animalSpeciesService;
    @GetMapping("/animal-species")
    @Override
    public List<AnimalSpeciesDTO> get() {
        List<AnimalSpeciesDTO> animalSpeciesList = animalSpeciesService.get();
        return animalSpeciesList;
    }

    @GetMapping("/animal-species/{id}")
    @Override
    public AnimalSpeciesDTO get(@PathVariable Integer id) {
        AnimalSpeciesDTO animalSpecies = animalSpeciesService.get(id);
        return  animalSpecies;
    }

    @PostMapping("/create-animal-species")
    @Override
    public AnimalSpeciesDTO create(@RequestBody @Valid CreateAnimalSpeciesDTO dto) {
        AnimalSpeciesDTO animalSpecies = animalSpeciesService.create(dto);
        return animalSpecies;
    }

    @Override
    @PostMapping("/update-animal-species/{id}")
    public AnimalSpeciesDTO update(@PathVariable Integer id, UpdateAnimalSpeciesDTO dto) {
        AnimalSpeciesDTO animalSpecies = animalSpeciesService.update(id, dto);
        return animalSpecies;
    }

    @Override
    public AnimalSpeciesDTO delete(Integer id) {
        return null;
    }
}
