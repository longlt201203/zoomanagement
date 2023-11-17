package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.errors.ZooManagementException;

import java.util.List;

public interface MealRecordsService {
    void generateMealRecords();

    List<MealRecord> getMealRecords(GetMealRecordsParams params);
    MealRecord updateMealRecordStatus(Integer id, UpdateMealRecordStatusDto dto) throws ZooManagementException;
}
