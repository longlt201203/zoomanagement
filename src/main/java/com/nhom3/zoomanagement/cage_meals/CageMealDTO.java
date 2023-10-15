package com.nhom3.zoomanagement.cage_meals;

import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.cages.CageDTO;
import com.nhom3.zoomanagement.meal_records.MealRecordDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CageMealDTO {
    public static CageMealDTO fromCageMeal(CageMeal cageMeal, boolean hasCage, boolean hasMeal, boolean hasCreatedBy) {
        CageMealDTO cageMealDTO = new CageMealDTO();
        cageMealDTO.setId(cageMeal.getId());
        cageMealDTO.setFood(cageMeal.getFood());
        cageMealDTO.setTime(cageMeal.getTime());
        if (hasCage) {
            cageMealDTO.setCage(CageDTO.fromCage(cageMeal.getCage(),false, false, false, false, false, false));
        }
        if (hasMeal) {
            cageMealDTO.setMealRecordDTOList(MealRecordDTO.fromMealRecordList(cageMeal.getMealRecordList(), false, false));
        }
        if(hasCreatedBy){
            cageMealDTO.setCreatedBy(AccountDTO.fromAccount(cageMeal.getCreatedBy(), false));
        }
        return cageMealDTO;
    }
    public static List<CageMealDTO> fromCageMealList(List<CageMeal> mealScheduleList, boolean hasCage, boolean hasMeal, boolean hasCreatedBy) {
        List<CageMealDTO> mealScheduleDTOList = new ArrayList<>();
        for (CageMeal mealSchedule : mealScheduleList) {
            CageMealDTO mealScheduleDTO = fromCageMeal(mealSchedule, hasCage, hasMeal, hasCreatedBy);
            mealScheduleDTOList.add(mealScheduleDTO);
        }
        return mealScheduleDTOList;
    }
    private Integer id;
    private String time;
    private CageDTO cage;
    private String food;
    private AccountDTO createdBy;
    private List<MealRecordDTO> mealRecordDTOList;
}
