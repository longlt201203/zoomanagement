package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.cages.CageDTO;
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
    public static MealDTO fromMeal(Meal meal, boolean hasCage) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setFood(meal.getFood());
        mealDTO.setTime(meal.getTime());
        mealDTO.setQuantity(meal.getQuantity());
        mealDTO.setStatus(meal.getStatus());
        mealDTO.setNote(meal.getNote());
        if (hasCage) {
            mealDTO.setCage(CageDTO.fromCage(meal.getCage(), false, false, false, false));
        }
        return mealDTO;
    }

    public static List<MealDTO> fromMealList(List<Meal> mealList, boolean hasCage) {
        List<MealDTO> mealDTOList = new ArrayList<>();
        for (Meal meal : mealList) {
            MealDTO mealDTO = fromMeal(meal,hasCage);
            mealDTOList.add(mealDTO);
        }
        return mealDTOList;
    }

    private Integer id;
    private Date time;
    private String food;
    private String quantity;
    private Enums.MealStatusEnum status;
    private String note;
    private CageDTO cage;
}
