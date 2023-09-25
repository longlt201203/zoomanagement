package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.cages.CagesDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealsDTO {
    public static MealsDTO fromMeal(Meal meal, boolean hasCage) {
        MealsDTO mealDTO = new MealsDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setFood(meal.getFood());
        mealDTO.setTime(meal.getTime());
        mealDTO.setQuantity(meal.getQuantity());
        mealDTO.setStatus(meal.getStatus());
        mealDTO.setNote(meal.getNote());
        if (hasCage) {
            mealDTO.setCage(CagesDTO.fromCage(meal.getCage(), false, false, false));
        }
        return mealDTO;
    }

    private Integer id;
    private Date time;
    private String food;
    private String quantity;
    private Enums.MealStatusEnum status;
    private String note;
    private CagesDTO cage;
}
