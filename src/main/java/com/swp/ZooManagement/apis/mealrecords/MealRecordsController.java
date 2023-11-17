package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/meal-records")
public interface MealRecordsController {
    @GetMapping("/")
    List<MealRecordResponseDto> getMealRecords(GetMealRecordsParams params);
    @PutMapping("/{id}")
    MealRecordResponseDto updateMealRecordStatus(@PathVariable("id") Integer id, @Valid @RequestBody UpdateMealRecordStatusDto dto) throws ZooManagementException;
}
