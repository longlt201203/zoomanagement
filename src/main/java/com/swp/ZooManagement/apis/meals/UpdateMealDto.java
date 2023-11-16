package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.apis.foods.Food;
import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateMealDto implements DtoBase<Meal> {
    @NotNull(message = "Time is  required")
    private Instant time;

    @Valid
    @NotNull(message = "Meal details is required")
    private List<UpdateMealDetailDto> details;

    @Override
    public Meal toEntity() {
        Meal meal = new Meal();
        meal.setTime(time);

        List<MealDetail> mealDetails = new ArrayList<>();
        for (UpdateMealDetailDto updateMealDetailDto : details) {
            MealDetail detail = new MealDetail();
            Food food = new Food();
            food.setId(updateMealDetailDto.getFoodId());
            detail.setFood(food);
            detail.setAmount(updateMealDetailDto.getAmount());
            mealDetails.add(detail);
        }
        meal.setDetails(mealDetails);
        return meal;
    }
}
