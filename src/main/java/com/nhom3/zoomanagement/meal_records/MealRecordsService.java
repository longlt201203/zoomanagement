package com.nhom3.zoomanagement.meal_records;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.cage_meals.CageMeal;
import com.nhom3.zoomanagement.cage_meals.CageMealsRepository;
import com.nhom3.zoomanagement.utils.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MealRecordsService implements IMealRecordsService {
    @Autowired
    MealRecordsRepository mealRecordsRepository;

    @Autowired
    CageMealsRepository cageMealsRepository;

    @Override
    public List<MealRecordDTO> get() {
        List<MealRecord> mealRecordList = mealRecordsRepository.findAll();
        return MealRecordDTO.fromMealRecordList(mealRecordList, true, true);
    }

    @Override
    public MealRecordDTO get(Integer id) throws AppServiceException {
        MealRecord mealRecord = mealRecordsRepository.findById(id).orElseThrow(() -> new AppServiceException((new ErrorReport("Meal Record not found"))));
        return MealRecordDTO.fromMealRecord(mealRecord, true, true);
    }

    @Override
    public MealRecordDTO create(CreateMealRecordDTO dto) throws BadRequestException {
        MealRecord mealRecord = new MealRecord();
        CageMeal cageMeal = cageMealsRepository.findById(dto.getCageMealId()).orElseThrow(() -> new AppServiceException((new ErrorReport("Cage Meal not found"))));
        mealRecord.setStatus(Enums.MealStatusEnum.valueOf(dto.getStatus()));
        mealRecord.setCageMeal(cageMeal);
        mealRecord.setUpdatedAt(new Date());
        mealRecord= mealRecordsRepository.save(mealRecord);
        return MealRecordDTO.fromMealRecord(mealRecord, true, true);
    }

    @Override
    public MealRecordDTO update(Integer id, CreateMealRecordDTO dto) throws BadRequestException {
        MealRecord mealRecord = mealRecordsRepository.findById(id).orElseThrow(() -> new AppServiceException((new ErrorReport("Meal Record not found"))));
        CageMeal cageMeal = cageMealsRepository.findById(dto.getCageMealId()).orElseThrow(() -> new AppServiceException((new ErrorReport("Cage Meal not found"))));
        mealRecord.setStatus(Enums.MealStatusEnum.valueOf(dto.getStatus()));
        mealRecord.setCageMeal(cageMeal);
        mealRecord.setUpdatedAt(new Date());
        mealRecord= mealRecordsRepository.save(mealRecord);
        return MealRecordDTO.fromMealRecord(mealRecord, true, true);
    }

    @Override
    public MealRecordDTO delete(Integer id) {
        return null;
    }
}
