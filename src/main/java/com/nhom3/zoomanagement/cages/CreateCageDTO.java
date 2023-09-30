package com.nhom3.zoomanagement.cages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCageDTO {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 10, message = "code cannot be more than 10 characters!")
    private String code;
    @Size(max = 255, message = "Description cannot be more than 255 characters!")
    private String description;
    @NotBlank(message = "Area field cannot be blank")
    private String area;
    @NotBlank(message = "Species field cannot be blank")
    private String animalSpecies;
}
