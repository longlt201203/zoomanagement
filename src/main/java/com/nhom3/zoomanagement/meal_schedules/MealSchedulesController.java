package com.nhom3.zoomanagement.meal_schedules;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meal-schedule")
public class MealSchedulesController implements IMealSchedulesController{
    @Autowired
    MealSchedulesService mealSchedulesService;

    @Override
    public List<MealScheduleDTO> get() {
        List<MealScheduleDTO> mealScheduleList = mealSchedulesService.get();
        return mealScheduleList;
    }

    @Override
    public MealScheduleDTO get(Integer id) throws AppServiceException {
        MealScheduleDTO mealScheduleDTO = mealSchedulesService.get(id);
        return mealScheduleDTO;
    }

    @Override
    public MealScheduleDTO create(CreateMealScheduleDTO dto) throws BadRequestException {
        MealScheduleDTO mealScheduleDTO = mealSchedulesService.create(dto);
        return mealScheduleDTO;
    }

    @Override
    public MealScheduleDTO update(Integer id, CreateMealScheduleDTO dto) throws BadRequestException {
        MealScheduleDTO mealScheduleDTO = mealSchedulesService.update(id, dto);
        return mealScheduleDTO;
    }

    @Override
    public MealScheduleDTO delete(Integer id) {
        return null;
    }
}
