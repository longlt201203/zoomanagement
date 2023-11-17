package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MealRecordsControllerImpl implements MealRecordsController {
    @Autowired
    private MealRecordsService mealRecordsService;

    @Override
    public List<MealRecordResponseDto> getMealRecords(GetMealRecordsParams params) {
        List<MealRecordResponseDto> mealRecordResponseDtoList = new ArrayList<>();
        List<MealRecord> mealRecords = mealRecordsService.getMealRecords(params);
        for (MealRecord mealRecord : mealRecords) {
            mealRecordResponseDtoList.add(mealRecord.toResponseDto());
        }
        return mealRecordResponseDtoList;
    }

    @Override
    public MealRecordResponseDto updateMealRecordStatus(Integer id, UpdateMealRecordStatusDto dto) throws ZooManagementException {
        MealRecord mealRecord = mealRecordsService.updateMealRecordStatus(id, dto);
        return mealRecord.toResponseDto();
    }
}
