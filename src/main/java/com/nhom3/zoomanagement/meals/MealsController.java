package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.meal_schedules.MealScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/meal")
public class MealsController implements IMealsController{
    @Autowired
    MealsService mealsService;

    @Override
    public List<MealDTO> get() {
        List<MealDTO> mealList = mealsService.get();
        return mealList;
    }

    @Override
    public MealDTO get(Integer id) throws AppServiceException {
        MealDTO meal = mealsService.get(id);
        return meal;
    }

    @Override
    public MealDTO create(CreateMealsDTO dto) throws BadRequestException {
        MealDTO meal = mealsService.create(dto);
        return meal;
    }

    @Override
    public MealDTO update(Integer id, CreateMealsDTO dto) throws BadRequestException {
        MealDTO meal = mealsService.update(id, dto);
        return meal;
    }

    @Override
    public MealDTO delete(Integer id) {
        return null;
    }
}
