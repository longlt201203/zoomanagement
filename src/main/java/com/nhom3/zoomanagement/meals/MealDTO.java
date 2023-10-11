package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.cages.CageDTO;
import com.nhom3.zoomanagement.meal_schedules.MealSchedule;
import com.nhom3.zoomanagement.meal_schedules.MealScheduleDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    public static MealDTO fromMeal(Meal meal, boolean hasMealSchedule) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setFood(meal.getFood());
        mealDTO.setTime(meal.getTime());
        mealDTO.setStatus(meal.getStatus());
        if (hasMealSchedule) {
            mealDTO.setMealSchedule(MealScheduleDTO.fromMealSchedule(meal.getMealSchedule(), false, false));
        }
        return mealDTO;
    }

    public static List<MealDTO> fromMealList(List<Meal> mealList, boolean hasMealSchedule) {
        List<MealDTO> mealDTOList = new ArrayList<>();
        for (Meal meal : mealList) {
            MealDTO mealDTO = fromMeal(meal,hasMealSchedule);
            mealDTOList.add(mealDTO);
        }
        return mealDTOList;
    }

    private Integer id;
    private String time;
    private String food;
    private Enums.MealStatusEnum status;
    private MealScheduleDTO mealSchedule;
}
