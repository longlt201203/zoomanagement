package com.swp.ZooManagement.apis.foods;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodsController extends AbstractZooManagementController<Food, Integer, CreateFoodDto, UpdateFoodDto, FilterFoodDto, FoodResponseDto> {
}
