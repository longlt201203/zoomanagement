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
        List<AnimalSpecies> animalSpeciesList = animalSpeciesService.get();
        return AnimalSpeciesDTO.fromAnimaSpecielList(animalSpeciesList, false, false);
    }

    @GetMapping("/animal-species/{id}")
    @Override
    public AnimalSpeciesDTO get(@PathVariable Integer id) {
        AnimalSpecies animalSpecies = animalSpeciesService.get(id);
        return  AnimalSpeciesDTO.fromAnimalSpecie(animalSpecies, false, false);
    }

    @PostMapping("/create-animal-species")
    @Override
    public AnimalSpeciesDTO create(@RequestBody @Valid CreateAnimalSpeciesDTO dto) {
        AnimalSpecies animalSpecies = animalSpeciesService.create(dto);
        return AnimalSpeciesDTO.fromAnimalSpecie(animalSpecies, false, false);
    }

    @Override
    @PostMapping("/update-animal-species/{id}")
    public AnimalSpeciesDTO update(@PathVariable Integer id, UpdateAnimalSpeciesDTO dto) {
        AnimalSpecies animalSpecies = animalSpeciesService.update(id, dto);
        return AnimalSpeciesDTO.fromAnimalSpecie(animalSpecies, false, false);
    }

    @Override
    public AnimalSpeciesDTO delete(Integer id) {
        return null;
    }
}
