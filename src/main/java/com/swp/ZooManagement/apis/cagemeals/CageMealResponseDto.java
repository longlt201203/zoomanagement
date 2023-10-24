package com.swp.ZooManagement.apis.cagemeals;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.mealrecords.MealRecordResponseDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CageMealResponseDto {
    private Integer id;
    private Instant time;
    private String food;
    private AccountCreatorDto createdBy;
    private List<MealRecordResponseDto> meals;
}
