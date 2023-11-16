package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.DateValidate;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.AnimalGenderEnum;
import com.swp.ZooManagement.utils.enums.AnimalStatusEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UpdateAnimalDto implements DtoBase<Animal> {
    @NotNull(message = "Name field cannot be null")
    @NotBlank(message = "Name field cannot be blank")
    @Size(max = 100, message = "Name cannot be more than 100 characters!")
    private String name;

    @Size(max = 100, message = "Nation cannot be more than 100 characters!")
    private String nation;

    @DateValidate
    private String dob;

    @IsEnum(enumClass = AnimalGenderEnum.class)
    private String gender;

    @IsEnum(enumClass = AnimalStatusEnum.class)
    private String status;

    @NotNull(message = "Description cannot be blank")
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description cannot be more than 1000 characters!")
    private String description;

    @Size(max = 255, message = "Note cannot be more than 255 characters!")
    private String note;

    @NotNull(message= "Animal Species field can not be null")
    private Integer speciesId;

    private Integer cageId;

    @NotNull(message = "Image list cannot be null")
    private List<String> imageList;

    @NotNull(message = "Weight cannot be null")
    @DecimalMin(value = "0.0", message = "Weight must be between 0 and 10000")
    @DecimalMax(value = "10000.0", message = "Weight must be between 0 and 10000")
    private Double weight;

    @NotNull(message = "Height cannot be null")
    @DecimalMin(value = "0.0", message = "Height must be between 0 and 10")
    @DecimalMax(value = "10.0", message = "Height must be between 0 and 10")
    private Double height;

    @NotNull(message = "Length cannot be null")
    @DecimalMin(value = "0.0", message = "Length must be between 0 and 50")
    @DecimalMax(value = "50.0", message = "Length must be between 0 and 50")
    private Double length;

    @Size(max = 255, message = "Feeding Guide max length is 1000 characters")
    private String feedingGuide;

    @Override
    public Animal toEntity() {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setNation(nation);
        animal.setDob(Instant.parse(dob));
        animal.setGender(AnimalGenderEnum.valueOf(gender));
        animal.setStatus(AnimalStatusEnum.valueOf(status));
        animal.setDescription(description);
        animal.setNote(note);

        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setId(speciesId);
        animal.setSpecies(animalSpecies);

        if (cageId != null) {
            Cage cage = new Cage();
            cage.setId(cageId);
            animal.setCage(cage);
        }

        animal.setImageList(String.join(";", imageList));
        animal.setWeight(weight);
        animal.setHeight(height);
        animal.setLength(length);

        return animal;
    }
}
