package com.nhom3.zoomanagement.cages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCageDTO {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 10, message = "code cannot be more than 10 characters!")
    private String code;
    private String description;
    @NotNull(message = "Area field cannot be blank")
    private Integer areaId;
    @NotNull(message = "Species field cannot be blank")
    private Integer animalSpeciesId;
}
