package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.apis.cagemeals.CageMealsRepository;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.apis.cages.CagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MealRecordsScheduler {
    @Autowired
    private MealRecordsRepository mealRecordsRepository;

    @Autowired
    private CageMealsRepository cageMealsRepository;

    @Autowired
    private CagesRepository cagesRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void createDailyMeal() {
        List<CageMeal> cageMeals = cageMealsRepository.findAll();
        List<MealRecord> mealRecords = new ArrayList<>();
        for (CageMeal cageMeal : cageMeals) {
            MealRecord mealRecord = new MealRecord();
            mealRecord.setCageMeal(cageMeal);
            mealRecords.add(mealRecord);
        }
        mealRecordsRepository.saveAll(mealRecords);
    }
}
