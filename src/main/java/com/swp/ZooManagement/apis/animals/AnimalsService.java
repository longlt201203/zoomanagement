package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesRepository;
import com.swp.ZooManagement.apis.auth.AuthenticationService;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.apis.cages.CagesRepository;
import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
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

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<Animal> findAll(FilterAnimalDto filterAnimalDto) {
        Account currentUser = authenticationService.getCurrentUser();
        if (currentUser != null && currentUser.getRole() == AccountRoleEnum.TRAINER) {
            filterAnimalDto.setManagerId(currentUser.getId());
        }
        return super.findAll(filterAnimalDto);
    }

    @Override
    protected void beforeCreate(Animal entity) throws ZooManagementException {
        List<ValidationError> errors = new ArrayList<>();

        Optional<AnimalSpecies> findAnimalSpeciesResult = animalSpeciesRepository.findById(entity.getSpecies().getId());
        if (findAnimalSpeciesResult.isEmpty()) {
            errors.add(new ValidationError("speciesId", entity.getSpecies().getId(), "Species not found"));
        } else {
            entity.setSpecies(findAnimalSpeciesResult.get());
        }

        if (entity.getCage() != null) {
            Optional<Cage> findCageResult = cagesRepository.findById(entity.getCage().getId());
            if (findCageResult.isEmpty()) {
                errors.add(new ValidationError("cageId", entity.getCage().getId(), "Cage not found"));
            } else {
                Cage cage = findCageResult.get();
                if (cage.getAnimals().size()+1 > cage.getCapacity()) {
                    errors.add(new ValidationError("cageId", entity.getCage().getId(), "Cage's max capacity is " + cage.getCapacity()));
                } else {
                    entity.setCage(findCageResult.get());
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void beforeUpdate(Animal oldEntity, Animal newEntity) throws ZooManagementException {
        List<ValidationError> errors = new ArrayList<>();

        Optional<AnimalSpecies> findAnimalSpeciesResult = animalSpeciesRepository.findById(newEntity.getSpecies().getId());
        if (findAnimalSpeciesResult.isEmpty()) {
            errors.add(new ValidationError("speciesId", newEntity.getSpecies().getId(), "Species not found"));
        } else {
            newEntity.setSpecies(findAnimalSpeciesResult.get());
        }

        if (newEntity.getCage() != null) {
            Optional<Cage> findCageResult = cagesRepository.findById(newEntity.getCage().getId());
            if (findCageResult.isEmpty()) {
                errors.add(new ValidationError("cageId", newEntity.getCage().getId(), "Cage not found"));
            } else {
                Cage cage = findCageResult.get();
                if (!oldEntity.getCage().getId().equals(cage.getId()) && cage.getAnimals().size()+1 > cage.getCapacity()) {
                    errors.add(new ValidationError("cageId", newEntity.getCage().getId(), "Cage's max capacity is " + cage.getCapacity()));
                } else {
                    newEntity.setCage(findCageResult.get());
                }
            }
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
