package com.swp.ZooManagement.apis.mealrecords;

import lombok.Data;

import java.time.Instant;

@Data
public class GetMealRecordsParams {
    private Integer animalId;
    private Instant date;
}
