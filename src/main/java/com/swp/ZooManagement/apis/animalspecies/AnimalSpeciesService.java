package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalSpeciesService extends AbstractZooManagementService<AnimalSpecies, Integer, CreateAnimalSpeciesDto, UpdateAnimalSpeciesDto, FilterAnimalSpeciesDto> {
    @Override
    protected void beforeCreate(AnimalSpecies entity) throws ZooManagementException {
        AnimalSpeciesRepository repository = (AnimalSpeciesRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<AnimalSpecies> findResult;

        // Check name
        findResult = repository.findByName(entity.getName());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("name", entity.getName(), "This name already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void beforeUpdate(AnimalSpecies oldEntity, AnimalSpecies newEntity) throws ZooManagementException {
        AnimalSpeciesRepository repository = (AnimalSpeciesRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<AnimalSpecies> findResult;

        // Check name
        findResult = repository.findByName(newEntity.getName());
        if (findResult.isPresent() && !findResult.get().getId().equals(oldEntity.getId())) {
            errors.add(new ValidationError("name", newEntity.getName(), "This name already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setName(newEntity.getName());
        oldEntity.setImage(newEntity.getImage());
        oldEntity.setDescription(newEntity.getDescription());
    }

    @Override
    public AnimalSpecies delete(Integer id) throws ZooManagementException {
        return null;
    }
}
