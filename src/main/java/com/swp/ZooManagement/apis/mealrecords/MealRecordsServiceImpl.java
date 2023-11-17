package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.meals.Meal;
import com.swp.ZooManagement.apis.meals.MealsRepository;
import com.swp.ZooManagement.errors.EntityNotFoundErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealRecordsServiceImpl implements MealRecordsService {
    @Autowired
    private MealsRepository mealsRepository;

    @Autowired
    private MealRecordsRepository mealRecordsRepository;

    @Override
    @Scheduled(cron = "0 1 0 * * *")
    public void generateMealRecords() {
        List<Meal> meals = mealsRepository.findAll();
        List<MealRecord> mealRecords = new ArrayList<>();
        for (Meal meal : meals) {
            MealRecord mealRecord = new MealRecord();
            mealRecord.setMeal(meal);
            mealRecord.setStatus(MealStatusEnum.NOT_FEED);
            mealRecords.add(mealRecord);
        }
        mealRecordsRepository.saveAll(mealRecords);
        System.out.println("Meal Records are generated!");
    }

    @Override
    public List<MealRecord> getMealRecords(GetMealRecordsParams params) {
        List<MealRecord> mealRecords = mealRecordsRepository.findByAnimalAndCreateAt(params.getAnimalId(), params.getDate());
        return mealRecords;
    }

    @Override
    public MealRecord updateMealRecordStatus(Integer id, UpdateMealRecordStatusDto dto) throws ZooManagementException {
        Optional<MealRecord> findMealRecordResult = mealRecordsRepository.findById(id);
        if (findMealRecordResult.isEmpty()) {
            throw new ZooManagementException(new EntityNotFoundErrorReport("id", id.toString()));
        }
        MealRecord mealRecord = findMealRecordResult.get();
        mealRecord.setStatus(MealStatusEnum.valueOf(dto.getStatus()));
        mealRecordsRepository.save(mealRecord);
        return mealRecord;
    }
}
