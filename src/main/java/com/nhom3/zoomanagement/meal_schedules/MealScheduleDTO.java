package com.nhom3.zoomanagement.meal_schedules;

import com.nhom3.zoomanagement.cages.CageDTO;
import com.nhom3.zoomanagement.meals.Meal;
import com.nhom3.zoomanagement.meals.MealDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealScheduleDTO {
    public static MealScheduleDTO fromMealSchedule(MealSchedule mealSchedule, boolean hasCage, boolean hasMeal) {
        MealScheduleDTO mealScheduleDTO = new MealScheduleDTO();
        mealScheduleDTO.setId(mealSchedule.getId());
        mealScheduleDTO.setDate(mealSchedule.getDate());
        if (hasCage) {
            mealScheduleDTO.setCage(CageDTO.fromCage(mealSchedule.getCage(), false, false, false, false));
        }
        if (hasMeal) {
            mealScheduleDTO.setMealList(MealDTO.fromMealList(mealSchedule.getMealList(), false));
        }
        return mealScheduleDTO;
    }
    public static List<MealScheduleDTO> fromMealScheduleList(List<MealSchedule> mealScheduleList, boolean hasCage, boolean hasMeal) {
        List<MealScheduleDTO> mealScheduleDTOList = new ArrayList<>();
        for (MealSchedule mealSchedule : mealScheduleList) {
            MealScheduleDTO mealScheduleDTO = fromMealSchedule(mealSchedule, hasCage, hasMeal);
            mealScheduleDTOList.add(mealScheduleDTO);
        }
        return mealScheduleDTOList;
    }
    private Integer id;
    private LocalDate date;
    private CageDTO cage;
    private List<MealDTO> mealList;
}
