package com.swp.ZooManagement.apis.foods;

import com.swp.ZooManagement.utils.enums.FoodTypeEnum;
import lombok.Data;

@Data
public class FoodResponseDto {
    private Integer id;
    private FoodTypeEnum type;
    private String name;
    private String unit;
    private String description;
}
