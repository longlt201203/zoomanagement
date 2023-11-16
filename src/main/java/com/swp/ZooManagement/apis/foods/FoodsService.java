package com.swp.ZooManagement.apis.foods;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodsService extends AbstractZooManagementService<Food, Integer, CreateFoodDto, UpdateFoodDto, FilterFoodDto> {
    @Override
    protected void beforeCreate(Food entity) throws ZooManagementException {
        FoodsRepository repository = (FoodsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();

        Optional<Food> findFoodByNameResult = repository.findByName(entity.getName());
        if (findFoodByNameResult.isPresent()) {
            errors.add(new ValidationError("name", entity.getName(), "Food name is already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void beforeUpdate(Food oldEntity, Food newEntity) throws ZooManagementException {
        FoodsRepository repository = (FoodsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();

        Optional<Food> findFoodByNameResult = repository.findByName(newEntity.getName());
        if (!oldEntity.getName().equals(newEntity.getName()) && findFoodByNameResult.isPresent()) {
            errors.add(new ValidationError("name", newEntity.getName(), "Food name is already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setName(newEntity.getName());
        oldEntity.setUnit(newEntity.getUnit());
        oldEntity.setDescription(newEntity.getDescription());
        oldEntity.setType(newEntity.getType());
    }

    @Override
    public Food delete(Integer id) throws ZooManagementException {
        return null;
    }
}
