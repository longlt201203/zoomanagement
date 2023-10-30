package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meal-records")
public class MealRecordsController extends AbstractZooManagementController<MealRecord, Integer, CreateMealRecordDto, UpdateMealRecordDto, FilterMealRecordDto, MealRecordResponseDto> {
}
