package com.swp.ZooManagement.apis.cagemeals;

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
public class CageMealsService extends AbstractZooManagementService<CageMeal, Integer, CreateCageMealDto, UpdateCageMealDto, FilterCageMealDto> {
    @Autowired
    private CagesRepository cagesRepository;

    @Override
    protected void berforeCreate(CageMeal entity) throws ZooManagementException {
        CageMealsRepository repository = (CageMealsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();

        // Check cage
        Optional<Cage> findCageResult = cagesRepository.findById(entity.getCage().getId());
        if (findCageResult.isEmpty()) {
            errors.add(new ValidationError("cageId", entity.getCage().getId(), "Cage is not existed"));
        }

        // Check time and cageId
        Optional<CageMeal> findResult = repository.findByCageIdAndTime(entity.getCage().getId(), entity.getTime());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("time", entity.getTime(), "This cage already have a meal at this time"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        entity.setCage(findCageResult.get());
    }

    @Override
    protected void berforeUpdate(CageMeal oldEntity, CageMeal newEntity) throws ZooManagementException {
        CageMealsRepository repository = (CageMealsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();

        // Check cage
        Optional<Cage> findCageResult = cagesRepository.findById(newEntity.getCage().getId());
        if (findCageResult.isEmpty()) {
            errors.add(new ValidationError("cageId", newEntity.getCage().getId(), "Cage is not existed"));
        }

        // Check time and cageId
        Optional<CageMeal> findResult = repository.findByCageIdAndTime(newEntity.getCage().getId(), newEntity.getTime());
        if (findResult.isPresent() && !findResult.get().getId().equals(oldEntity.getId())) {
            errors.add(new ValidationError("time", newEntity.getTime(), "This cage already have a meal at this time"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setCage(findCageResult.get());
        oldEntity.setTime(newEntity.getTime());
        oldEntity.setFood(newEntity.getFood());
    }

    @Override
    public CageMeal delete(Integer id) throws ZooManagementException {
        CageMeal cageMeal = findById(id);
        repository.delete(cageMeal);
        return cageMeal;
    }
}
