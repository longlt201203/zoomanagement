package com.nhom3.zoomanagement.animal_species;

import com.nhom3.zoomanagement.errors.AppServiceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/animal-species")
public class AnimalSpeciesController implements IAnimalSpeciesController{
    @Autowired
    AnimalSpeciesService animalSpeciesService;

    @Override
    public List<AnimalSpeciesDTO> get() {
        List<AnimalSpeciesDTO> animalSpeciesList = animalSpeciesService.get();
        return animalSpeciesList;
    }


    @Override
    public AnimalSpeciesDTO get(@PathVariable Integer id) throws AppServiceException {
        AnimalSpeciesDTO animalSpecies = animalSpeciesService.get(id);
        return  animalSpecies;
    }


    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public AnimalSpeciesDTO create(@RequestBody @Valid CreateAnimalSpeciesDTO dto) throws AppServiceException {
        AnimalSpeciesDTO animalSpecies = animalSpeciesService.create(dto);
        return animalSpecies;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public AnimalSpeciesDTO update(@PathVariable Integer id, UpdateAnimalSpeciesDTO dto) throws AppServiceException {
        AnimalSpeciesDTO animalSpecies = animalSpeciesService.update(id, dto);
        return animalSpecies;
    }

    @Override
    public AnimalSpeciesDTO delete(Integer id) {
        return null;
    }
}
