package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreasService extends AbstractZooManagementService<Area, Integer, CreateAreaDto, UpdateAreaDto, FilterAreaDto> {

    public List<GetAreasWithStatisticsResult> findAllWithStatistics() {
        AreasRepository repository = (AreasRepository) this.repository;
        for (GetAreasWithStatisticsResult result : repository.findAllWithStatistics()) {
            System.out.println("Hello result: Animal count: " + result.getAnimalCount() + "; Cage count: " + result.getCageCount());
        }
        return repository.findAllWithStatistics();
    }

    @Override
    protected void berforeCreate(Area entity) throws ZooManagementException {
        AreasRepository repository = (AreasRepository) this.repository;

        List<ValidationError> errors = new ArrayList<>();
        Optional<Area> findResult;

        // Check code
        findResult = repository.findByCode(entity.getCode());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("code", entity.getCode(), "This code is already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void berforeUpdate(Area oldEntity, Area newEntity) throws ZooManagementException {
        AreasRepository repository = (AreasRepository) this.repository;

        List<ValidationError> errors = new ArrayList<>();
        Optional<Area> findResult;

        // Check code
        findResult = repository.findByCode(newEntity.getCode());
        if (findResult.isPresent() && !findResult.get().getId().equals(oldEntity.getId())) {
            errors.add(new ValidationError("code", newEntity.getCode(), "This code is already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setCode(newEntity.getCode());
        oldEntity.setName(newEntity.getName());
        oldEntity.setLocation(newEntity.getLocation());
    }

    @Override
    public Area delete(Integer id) throws ZooManagementException {

        return null;
    }
}
