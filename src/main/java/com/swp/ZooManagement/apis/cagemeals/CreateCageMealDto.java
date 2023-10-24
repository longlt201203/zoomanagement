package com.swp.ZooManagement.apis.cagemeals;

import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.DateValidate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateCageMealDto implements DtoBase<CageMeal> {
    @DateValidate
    private String time;

    @NotNull(message = "Food cannot be null")
    @NotBlank(message = "Food cannot be blank")
    private String food;

    @NotNull(message = "Cage cannot be null")
    private Integer cageId;

    @Override
    public CageMeal toEntity() {
        CageMeal cageMeal = new CageMeal();
        cageMeal.setTime(Instant.parse(time));
        cageMeal.setFood(food);
        Cage cage = new Cage();
        cage.setId(cageId);
        cageMeal.setCage(cage);
        return cageMeal;
    }
}
