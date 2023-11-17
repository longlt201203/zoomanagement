package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.meals.MealResponseDto;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class MealRecordResponseDto {
    private Integer id;
    private MealResponseDto meal;
    private MealStatusEnum status;
    private Instant createdAt;
    private Instant updatedAt;
//    private AccountCreatorDto updatedBy;
}
