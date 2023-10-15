package com.nhom3.zoomanagement.meal_records;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/meal-record")
public class MealRecordsController implements IMealRecordsController {
    @Autowired
    MealRecordsService mealsService;

    @Override
    public List<MealRecordDTO> get() {
        List<MealRecordDTO> mealList = mealsService.get();
        return mealList;
    }

    @Override
    public MealRecordDTO get(Integer id) throws AppServiceException {
        MealRecordDTO meal = mealsService.get(id);
        return meal;
    }

    @Override
    public MealRecordDTO create(CreateMealRecordDTO dto) throws BadRequestException {
        MealRecordDTO meal = mealsService.create(dto);
        return meal;
    }

    @Override
    public MealRecordDTO update(Integer id, CreateMealRecordDTO dto) throws BadRequestException {
        MealRecordDTO meal = mealsService.update(id, dto);
        return meal;
    }

    @Override
    public MealRecordDTO delete(Integer id) {
        return null;
    }
}
