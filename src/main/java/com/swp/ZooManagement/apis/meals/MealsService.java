package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealsService extends AbstractZooManagementService<Meal, Integer, CreateMealDto, UpdateMealDto, FilterMealDto> {
    @Override
    protected void beforeCreate(Meal entity) throws ZooManagementException {
//        List<ValidationError> errors = new ArrayList<>();
//
//        if (!errors.isEmpty()) {
//            throw new ZooManagementException(new ValidationErrorReport(errors));
//        }
    }

    @Override
    protected void beforeUpdate(Meal oldEntity, Meal newEntity) throws ZooManagementException {
        oldEntity.setTime(newEntity.getTime());
        List<MealDetail> oldDetails = oldEntity.getDetails();
        List<MealDetail> newDetails = newEntity.getDetails();
        oldDetails.clear();
        for (MealDetail mealDetail : newDetails) {
            mealDetail.setMeal(oldEntity);
            oldDetails.add(mealDetail);
        }
    }

    @Override
    public Meal delete(Integer id) throws ZooManagementException {
        Meal meal = findById(id);
        repository.delete(meal);
        return meal;
    }
}
