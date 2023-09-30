package com.nhom3.zoomanagement.animal_species;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnimalSpeciesDTO {
    @NotBlank(message = "Name field cannot be blank")
    @Size(max = 50, message = "Name cannot be more than 50 characters!")
    private String name;
    @Size(max = 255, message = "Description cannot be more than 255 characters!")
    private String description;
    private String image;
}
