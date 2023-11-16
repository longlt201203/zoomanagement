package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.foods.FoodResponseDto;
import lombok.Data;

@Data
public class MealDetailResponseDto {
    private Integer id;
    private FoodResponseDto food;
    private Double amount;
}
