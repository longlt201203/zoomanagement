package com.nhom3.zoomanagement.cage_meals;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCageMealDTO {
    @NotBlank(message = "Food field cannot be blank")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "time must have format HH:mm")
    private String time;
    @NotBlank(message = "Food field cannot be blank")
    private String food;
    @NotNull(message = "cage field cannot be null")
    private Integer cageId;

}
