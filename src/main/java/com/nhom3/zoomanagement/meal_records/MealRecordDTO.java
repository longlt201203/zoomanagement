package com.nhom3.zoomanagement.meal_records;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.cage_meals.CageMealDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealRecordDTO {
    public static MealRecordDTO fromMealRecord(MealRecord mealRecord, boolean hasCageMeal, boolean hasUpdatedBy) {
        MealRecordDTO mealRecordDTO = new MealRecordDTO();
        mealRecordDTO.setId(mealRecord.getId());
        mealRecordDTO.setStatus(mealRecord.getStatus());
        mealRecordDTO.setUpdatedAt(mealRecord.getUpdatedAt());
        if (hasCageMeal) {
            mealRecordDTO.setCageMealDTO(CageMealDTO.fromCageMeal(mealRecord.getCageMeal(), false, false, false));
        }
        if(hasUpdatedBy){
            mealRecordDTO.setUpdatedBy(AccountDTO.fromAccount(mealRecord.getUpdatedBy(), false));
        }
        return mealRecordDTO;
    }

    public static List<MealRecordDTO> fromMealRecordList(List<MealRecord> mealList, boolean hasCageMeal, boolean hasUpdatedBy) {
        List<MealRecordDTO> mealDTOList = new ArrayList<>();
        for (MealRecord meal : mealList) {
            MealRecordDTO mealDTO = fromMealRecord(meal,hasCageMeal, hasUpdatedBy);
            mealDTOList.add(mealDTO);
        }
        return mealDTOList;
    }

    private Integer id;
    private Enums.MealStatusEnum status;
    private CageMealDTO cageMealDTO;
    private AccountDTO updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;
}
