package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;

import java.time.Instant;

public interface MealRecordProjection {
    Integer getId();
    Instant getCreatedAt();
    MealStatusEnum getStatus();
    Instant getUpdatedAt();
    Account getUpdatedBy();
    CageMeal getCageMeal();
}
