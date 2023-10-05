package com.nhom3.zoomanagement.animal_species;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalSpeciesService implements IAnimalSpeciesService {
    @Autowired
    AnimalSpeciesRepository animalSpeciesRepository;
    @Override
    public AnimalSpeciesDTO create(CreateAnimalSpeciesDTO dto) {
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setName(dto.getName());
        animalSpecies.setImage(dto.getImage());
        animalSpecies.setDescription(dto.getDescription());
        animalSpecies = animalSpeciesRepository.save(animalSpecies);
        return AnimalSpeciesDTO.fromAnimalSpecie(animalSpecies, false, false);
    }

    @Override
    public List<AnimalSpeciesDTO> get() {
        List<AnimalSpecies> animalSpeciesList = animalSpeciesRepository.findAll();
        return AnimalSpeciesDTO.fromAnimaSpecielList(animalSpeciesList, false, false);
    }

    @Override
    public AnimalSpeciesDTO get(Integer id) {
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal Species not found"));
        return  AnimalSpeciesDTO.fromAnimalSpecie(animalSpecies, false, false);
    }

    @Override
    public AnimalSpeciesDTO update(Integer id, UpdateAnimalSpeciesDTO dto) {
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal Species not found"));
        animalSpecies.setName(dto.getName());
        animalSpecies.setImage(dto.getImage());
        animalSpecies.setDescription(dto.getDescription());
        animalSpecies = animalSpeciesRepository.save(animalSpecies);
        return AnimalSpeciesDTO.fromAnimalSpecie(animalSpecies, false, false);
    }

    @Override
    public AnimalSpeciesDTO delete(Integer id) {
        return null;
    }
}
