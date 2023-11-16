package com.swp.ZooManagement.apis.meals;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMealDetailDto {
    @NotNull(message = "Food is required")
    private Integer foodId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum amount must be greater than 0.0")
    private Double amount;
}
