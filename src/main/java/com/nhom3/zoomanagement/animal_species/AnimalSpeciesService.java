package com.nhom3.zoomanagement.animal_species;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnimalSpeciesService implements IAnimalSpeciesService {
    @Autowired
    AnimalSpeciesRepository animalSpeciesRepository;
    @Override
    public AnimalSpeciesDTO create(CreateAnimalSpeciesDTO dto) throws AppServiceException {
        if (animalSpeciesRepository.existsByName(dto.getName())) {
            throw new AppServiceException(new ErrorReport("Species name already existed"));
        }
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setName(dto.getName());
        animalSpecies.setImage(dto.getImage());
        animalSpecies.setDescription(dto.getDescription());
        animalSpecies = animalSpeciesRepository.save(animalSpecies);
        return AnimalSpeciesDTO.fromAnimalSpecies(animalSpecies, false, false,true);
    }

    @Override
    public List<AnimalSpeciesDTO> get() {
        List<AnimalSpecies> animalSpeciesList = animalSpeciesRepository.findAll();
        return AnimalSpeciesDTO.fromAnimaSpecieslList(animalSpeciesList, false, false, true);
    }

    @Override
    public AnimalSpeciesDTO get(Integer id) throws AppServiceException {
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal Species not found")));
        return  AnimalSpeciesDTO.fromAnimalSpecies(animalSpecies, false, false, true);
    }

    @Override
    public AnimalSpeciesDTO update(Integer id, UpdateAnimalSpeciesDTO dto) throws AppServiceException {
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal Species not found")));
        if (animalSpeciesRepository.existsByName(dto.getName()) && !dto.getName().matches(animalSpecies.getName())) {
            throw new AppServiceException(new ErrorReport("Species name already existed"));
        }
        animalSpecies.setName(dto.getName());
        animalSpecies.setImage(dto.getImage());
        animalSpecies.setDescription(dto.getDescription());
        animalSpecies = animalSpeciesRepository.save(animalSpecies);
        return AnimalSpeciesDTO.fromAnimalSpecies(animalSpecies, false, false, true);
    }

    @Override
    public AnimalSpeciesDTO delete(Integer id) {
        return null;
    }
}
