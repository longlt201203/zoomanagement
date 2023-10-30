package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.core.DtoBase;
import lombok.Data;

@Data
public class CreateMealRecordDto implements DtoBase<MealRecord> {
    private Integer cageMealId;

    @Override
    public MealRecord toEntity() {
        MealRecord mealRecord = new MealRecord();
        CageMeal cageMeal = new CageMeal();
        cageMeal.setId(cageMealId);
        mealRecord.setCageMeal(cageMeal);
        return mealRecord;
    }
}
