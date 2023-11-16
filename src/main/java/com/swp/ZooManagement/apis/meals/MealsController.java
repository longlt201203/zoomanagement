package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meals")
public class MealsController extends AbstractZooManagementController<Meal, Integer, CreateMealDto, UpdateMealDto, FilterMealDto, MealResponseDto> {
}
