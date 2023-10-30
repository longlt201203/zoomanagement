package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountsRepository;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesRepository;
import com.swp.ZooManagement.apis.areas.Area;
import com.swp.ZooManagement.apis.areas.AreasRepository;
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
public class CagesService extends AbstractZooManagementService<Cage, Integer, CreateCageDto, UpdateCageDto, FilterCageDto> {
    @Autowired
    private AreasRepository areasRepository;
    @Autowired
    private AnimalSpeciesRepository animalSpeciesRepository;
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    protected void beforeCreate(Cage entity) throws ZooManagementException {
        CagesRepository repository = (CagesRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<Cage> findResult;

        // Check code
        findResult = repository.findByCode(entity.getCode());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("code", entity.getCode(), "This code already existed"));
        }

        // Check area
        Optional<Area> findAreaResult = areasRepository.findById(entity.getArea().getId());
        if (!findAreaResult.isPresent()) {
            errors.add(new ValidationError("areaId", entity.getArea().getId(), "Area does not existed"));
        }

        // Check animal species
        Optional<AnimalSpecies> findAnimalSpeciesResult = animalSpeciesRepository.findById(entity.getAnimalSpecies().getId());
        if (!findAnimalSpeciesResult.isPresent()) {
            errors.add(new ValidationError("animalSpeciesId", entity.getAnimalSpecies().getId(), "Animal Species does not existed"));
        }

        // Check manager
        if (entity.getManagedBy() != null) {
            Optional<Account> findAccountResult = accountsRepository.findById(entity.getManagedBy().getId());
            if (findAccountResult.isEmpty()) {
                errors.add(new ValidationError("managedById", entity.getManagedBy().getId(), "Account does not existed"));
            } else {
                entity.setManagedBy(findAccountResult.get());
            }
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        entity.setArea(findAreaResult.get());
        entity.setAnimalSpecies(findAnimalSpeciesResult.get());
    }

    @Override
    protected void beforeUpdate(Cage oldEntity, Cage newEntity) throws ZooManagementException {
        CagesRepository repository = (CagesRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<Cage> findResult;

        // Check code
        findResult = repository.findByCode(newEntity.getCode());
        if (findResult.isPresent() && !findResult.get().getId().equals(oldEntity.getId())) {
            errors.add(new ValidationError("code", newEntity.getCode(), "This code already existed"));
        }

        // Check area
        Optional<Area> findAreaResult = areasRepository.findById(newEntity.getArea().getId());
        if (findAreaResult.isEmpty()) {
            errors.add(new ValidationError("areaId", newEntity.getArea().getId(), "Area does not existed"));
        }

        // Check animal species
        Optional<AnimalSpecies> findAnimalSpeciesResult = animalSpeciesRepository.findById(newEntity.getAnimalSpecies().getId());
        if (findAnimalSpeciesResult.isEmpty()) {
            errors.add(new ValidationError("animalSpeciesId", newEntity.getAnimalSpecies().getId(), "Animal Species does not existed"));
        }

        // Check manager
        if (newEntity.getManagedBy() != null) {
            Optional<Account> findAccountResult = accountsRepository.findById(newEntity.getManagedBy().getId());
            if (findAccountResult.isEmpty()) {
                errors.add(new ValidationError("managedById", newEntity.getManagedBy().getId(), "Account does not existed"));
            } else {
                oldEntity.setManagedBy(findAccountResult.get());
            }
        } else if (oldEntity.getManagedBy() != null) {
            oldEntity.setManagedBy(null);
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setCode(newEntity.getCode());
        oldEntity.setArea(findAreaResult.get());
        oldEntity.setAnimalSpecies(findAnimalSpeciesResult.get());
        oldEntity.setDescription(newEntity.getDescription());
    }

    @Override
    public Cage delete(Integer id) throws ZooManagementException {
        return null;
    }
}
