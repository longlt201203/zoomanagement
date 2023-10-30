package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.core.DtoBase;
import lombok.Data;

@Data
public class UpdateMealRecordDto implements DtoBase<MealRecord> {
    @Override
    public MealRecord toEntity() {
        return null;
    }
}
