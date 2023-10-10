package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.meal_schedules.MealSchedule;
import com.nhom3.zoomanagement.meal_schedules.MealSchedulesRepository;
import com.nhom3.zoomanagement.utils.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealsService implements IMealsService{
    @Autowired
    MealsRepository mealsRepository;

    @Autowired
    MealSchedulesRepository mealSchedulesRepository;

    @Override
    public List<MealDTO> get() {
        List<Meal> mealList = mealsRepository.findAll();
        return MealDTO.fromMealList(mealList, true);
    }

    @Override
    public MealDTO get(Integer id) throws AppServiceException {
        Meal meal = mealsRepository.findById(id).orElseThrow(() -> new AppServiceException((new ErrorReport("Meal not found"))));
        return MealDTO.fromMeal(meal, true);
    }

    @Override
    public MealDTO create(CreateMealsDTO dto) throws BadRequestException {
        Meal meal = new Meal();
        MealSchedule mealSchedule = mealSchedulesRepository.findById(dto.getMealScheduleId()).orElseThrow(() -> new AppServiceException((new ErrorReport("Meal Schedule not found"))));
        meal.setTime(dto.getTime());
        meal.setFood(dto.getFood());
        meal.setStatus(Enums.MealStatusEnum.valueOf(dto.getStatus()));
        meal.setMealSchedule(mealSchedule);
        meal= mealsRepository.save(meal);
        return MealDTO.fromMeal(meal, true);
    }

    @Override
    public MealDTO update(Integer id, CreateMealsDTO dto) throws BadRequestException {
        Meal meal = mealsRepository.findById(id).orElseThrow(() -> new AppServiceException((new ErrorReport("Meal not found"))));
        MealSchedule mealSchedule = mealSchedulesRepository.findById(dto.getMealScheduleId()).orElseThrow(() -> new AppServiceException((new ErrorReport("Meal Schedule not found"))));
        meal.setTime(dto.getTime());
        meal.setFood(dto.getFood());
        meal.setMealSchedule(mealSchedule);
        meal.setStatus(Enums.MealStatusEnum.valueOf(dto.getStatus()));
        meal= mealsRepository.save(meal);
        return MealDTO.fromMeal(meal, true);
    }

    @Override
    public MealDTO delete(Integer id) {
        return null;
    }
}
