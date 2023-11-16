package com.swp.ZooManagement.apis.meals;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class MealResponseDto {
    private Integer id;
    private Instant time;
    private List<MealDetailResponseDto> details;
}
