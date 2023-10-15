package com.nhom3.zoomanagement.meal_records;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealRecordDTO {
    @NotBlank(message = "Status field cannot be blank")
    @ValueOfEnum(enumClass = Enums.MealStatusEnum.class, message = "status must be any of enum list")
    private String status;
    @NotNull(message = "mealSchedule field cannot be null")
    private Integer cageMealId;
}
