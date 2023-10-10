package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.meal_schedules.MealSchedule;
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
public class CreateMealsDTO {
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "time must have format HH:mm")
    private String time;
    @NotBlank(message = "Food field cannot be blank")
    private String food;
    @NotBlank(message = "Status field cannot be blank")
    @ValueOfEnum(enumClass = Enums.MealStatusEnum.class, message = "status must be any of enum list")
    private String status;
    @NotNull(message = "mealSchedule field cannot be null")
    private Integer mealScheduleId;
}
