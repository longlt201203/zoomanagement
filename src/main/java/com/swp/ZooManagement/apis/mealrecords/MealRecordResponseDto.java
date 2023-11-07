package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.cagemeals.CageMealResponseDto;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class MealRecordResponseDto {
    private Integer id;
    private MealStatusEnum status = MealStatusEnum.NOT_FEED;
    private Instant createdAt;
    private Instant updatedAt;
    private AccountCreatorDto updatedBy;
    private CageMealResponseDto cageMeal;
}
