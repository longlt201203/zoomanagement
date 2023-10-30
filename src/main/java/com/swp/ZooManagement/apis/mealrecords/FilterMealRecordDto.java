package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.FilterDtoBase;

import java.beans.ConstructorProperties;
import java.time.Instant;

public class FilterMealRecordDto extends FilterDtoBase<MealRecord> {
    private Integer cageId;
    private Instant date;

    @ConstructorProperties({ "page", "perPage", "cageId", "date" })
    public FilterMealRecordDto(Integer page, Integer perPage, Integer cageId, Instant date) {
        super(page, perPage);
        this.cageId = cageId;
        this.date = date != null ? date : Instant.now();
    }

    @Override
    public MealRecord toEntity() {
        MealRecord mealRecord = new MealRecord();
        CageMeal cageMeal = new CageMeal();
        Cage cage = new Cage();
        cage.setId(cageId);
        cageMeal.setCage(cage);
        mealRecord.setCageMeal(cageMeal);
        mealRecord.setCreatedAt(date);
        return mealRecord;
    }
}
