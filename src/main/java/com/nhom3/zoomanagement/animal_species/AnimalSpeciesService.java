package com.nhom3.zoomanagement.animal_species;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalSpeciesService implements IAnimalSpeciesService {
    @Autowired
    AnimalSpeciesRepository animalSpeciesRepository;
    @Override
    public AnimalSpecies create(CreateAnimalSpeciesDTO dto) {
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setName(dto.getName());
        animalSpecies.setImage(dto.getImage());
        animalSpecies.setDescription(dto.getDescription());
        animalSpecies = animalSpeciesRepository.save(animalSpecies);
        return animalSpecies;
    }

    @Override
    public List<AnimalSpecies> get() {
        List<AnimalSpecies> animalSpeciesList = animalSpeciesRepository.findAll();
        return animalSpeciesList;
    }

    @Override
    public AnimalSpecies get(Integer id) {
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal Species not found"));
        return  animalSpecies;
    }

    @Override
    public AnimalSpecies update(Integer id, UpdateAnimalSpeciesDTO dto) {
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal Species not found"));
        animalSpecies.setName(dto.getName());
        animalSpecies.setImage(dto.getImage());
        animalSpecies.setDescription(dto.getDescription());
        animalSpecies = animalSpeciesRepository.save(animalSpecies);
        return animalSpecies;
    }

    @Override
    public AnimalSpecies delete(Integer id) {
        return null;
    }
}
