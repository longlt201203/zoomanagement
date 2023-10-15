package com.nhom3.zoomanagement.cages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCageDTO {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 5, message = "code cannot be more than 5 characters!")
    @Pattern(regexp = "^[A-Za-z][0-9]{4}$", message = "Cage code must have format: Area Code + 4-digits number. Example: A0001, B0012")
    private String code;
    private String description;
    @NotNull(message= "Area field can not be empty")
    private Integer areaId;
    @NotNull(message= "Animal Species field can not be empty")
    private Integer animalSpeciesId;
    private String managedBy;
}
