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
public class CreateMealDto implements DtoBase<Meal> {
    @NotNull(message = "Animal is required")
    private Integer animalId;

    @NotNull(message = "Time is required")
    private Instant time;

    @Valid
    @NotNull(message = "Meal details is required")
    private List<CreateMealDetailDto> details;

    @Override
    public Meal toEntity() {
        Meal meal = new Meal();
        Animal animal = new Animal();
        animal.setId(animalId);
        meal.setAnimal(animal);
        meal.setTime(time);

        List<MealDetail> mealDetails = new ArrayList<>();
        for (CreateMealDetailDto createMealDto : details) {
            MealDetail detail = new MealDetail();
            Food food = new Food();
            food.setId(createMealDto.getFoodId());
            detail.setFood(food);
            detail.setAmount(createMealDto.getAmount());
            detail.setMeal(meal);
            mealDetails.add(detail);
        }
        meal.setDetails(mealDetails);
        return meal;
    }
}
