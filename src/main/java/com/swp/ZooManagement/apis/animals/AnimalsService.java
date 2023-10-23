package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesRepository;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.apis.cages.CagesRepository;
import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalsService extends AbstractZooManagementService<Animal, Integer, CreateAnimalDto, UpdateAnimalDto, FilterAnimalDto> {
    @Autowired
    private AnimalSpeciesRepository animalSpeciesRepository;

    @Autowired
    private CagesRepository cagesRepository;

    @Override
    protected void berforeCreate(Animal entity) throws ZooManagementException {
        List<ValidationError> errors = new ArrayList<>();

        Optional<AnimalSpecies> findAnimalSpeciesResult = animalSpeciesRepository.findById(entity.getSpecies().getId());
        if (findAnimalSpeciesResult.isEmpty()) {
            errors.add(new ValidationError("speciesId", entity.getSpecies().getId(), "Species not found"));
        } else {
            entity.setSpecies(findAnimalSpeciesResult.get());
        }

        Optional<Cage> findCageResult = cagesRepository.findById(entity.getCage().getId());
        if (findCageResult.isEmpty()) {
            errors.add(new ValidationError("cageId", entity.getCage().getId(), "Cage not found"));
        } else {
            entity.setCage(findCageResult.get());
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void berforeUpdate(Animal oldEntity, Animal newEntity) throws ZooManagementException {
        List<ValidationError> errors = new ArrayList<>();

        Optional<AnimalSpecies> findAnimalSpeciesResult = animalSpeciesRepository.findById(newEntity.getSpecies().getId());
        if (findAnimalSpeciesResult.isEmpty()) {
            errors.add(new ValidationError("speciesId", newEntity.getSpecies().getId(), "Species not found"));
        } else {
            newEntity.setSpecies(findAnimalSpeciesResult.get());
        }

        Optional<Cage> findCageResult = cagesRepository.findById(newEntity.getCage().getId());
        if (findCageResult.isEmpty()) {
            errors.add(new ValidationError("cageId", newEntity.getCage().getId(), "Cage not found"));
        } else {
            newEntity.setCage(findCageResult.get());
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setName(newEntity.getName());
        oldEntity.setNation(newEntity.getNation());
        oldEntity.setDescription(newEntity.getDescription());
        oldEntity.setDob(newEntity.getDob());
        oldEntity.setNote(newEntity.getNote());
        oldEntity.setGender(newEntity.getGender());
        oldEntity.setStatus(newEntity.getStatus());
        oldEntity.setImageList(newEntity.getImageList());
        oldEntity.setSpecies(newEntity.getSpecies());
        oldEntity.setCage(newEntity.getCage());
    }

    @Override
    public Animal delete(Integer id) throws ZooManagementException {
        return null;
    }
}
