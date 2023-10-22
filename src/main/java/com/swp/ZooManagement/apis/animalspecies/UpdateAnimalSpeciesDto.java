package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAnimalSpeciesDto implements DtoBase<AnimalSpecies> {
    @NotBlank(message = "Name field cannot be blank")
    @Size(max = 50, message = "Name cannot be more than 50 characters!")
    private String name;
    private String description;
    private String image;

    @Override
    public AnimalSpecies toEntity() {
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setName(name);
        animalSpecies.setDescription(description);
        animalSpecies.setImage(image);
        return animalSpecies;
    }
}
