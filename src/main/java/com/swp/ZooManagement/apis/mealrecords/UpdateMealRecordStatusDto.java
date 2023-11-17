package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import lombok.Data;

@Data
public class UpdateMealRecordStatusDto {
    @IsEnum(enumClass = MealStatusEnum.class)
    private String status;
}
