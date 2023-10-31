package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import lombok.Data;

@Data
public class UpdateMealRecordDto implements DtoBase<MealRecord> {
    @IsEnum(enumClass = MealStatusEnum.class)
    private String status;

    @Override
    public MealRecord toEntity() {
        MealRecord mealRecord = new MealRecord();
        mealRecord.setStatus(MealStatusEnum.valueOf(status));
        return mealRecord;
    }
}
