package com.swp.ZooManagement.apis.foods;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.FoodTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateFoodDto implements DtoBase<Food> {
    @IsEnum(enumClass = FoodTypeEnum.class)
    private String type;

    @NotNull(message = "Name cannot be blank")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 60, message = "Name length must be between 3 and 60 characters")
    private String name;

    @NotNull(message = "Unit cannot be blank")
    @NotBlank(message = "Unit cannot be blank")
    @Size(min = 3, max = 30, message = "Unit length must be between 3 and 30 characters")
    private String unit;

    @NotNull(message = "Description cannot be blank")
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 3, max = 255, message = "Unit length must be between 3 and 255 characters")
    private String description;

    @Override
    public Food toEntity() {
        Food food = new Food();
        food.setType(FoodTypeEnum.valueOf(type));
        food.setName(name);
        food.setUnit(unit);
        food.setDescription(description);
        return food;
    }
}
