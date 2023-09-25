package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.animal_images.AnimalImagesDTO;
import com.nhom3.zoomanagement.animal_species.AnimalSpeciesDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalsDTO {
    public static AnimalsDTO fromAnimal(Animal animal, boolean hasAnimalImage, boolean hasSpecies) {
        AnimalsDTO animalDTO = new AnimalsDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setNation(animal.getNation());
        animalDTO.setDob(animal.getDob());
        animalDTO.setGender(animal.getGender());
        animalDTO.setStatus(animal.getStatus());
        animalDTO.setDescription(animal.getDescription());
        animalDTO.setNote(animal.getNote());
        if (hasAnimalImage) {
            animalDTO.setImageList(AnimalImagesDTO.fromAnimalImageList(animal.getImageList(), false));
        }
        if(hasSpecies){
            animalDTO.setSpecies(AnimalSpeciesDTO.fromAnimalSpecie(animal.getSpecies(), false, false));
        }
        return animalDTO;
    }

    public static List<AnimalsDTO> fromAnimalList(List<Animal> animalList, boolean hasAnimalImage, boolean hasSpecies) {
        List<AnimalsDTO> animalDTOList = new ArrayList<>();
        for (Animal animal : animalList) {
            AnimalsDTO animalDTO = fromAnimal(animal, hasAnimalImage, hasSpecies);
            animalDTOList.add(animalDTO);
        }
        return animalDTOList;
    }
    private Integer id;
    private String name;
    private String nation;
    private Date dob;
    private Enums.AnimalGenderEnum gender;
    private Enums.AnimalStatusEnum status;
    private String description;
    private String note;
    private AnimalSpeciesDTO species;
    private List<AnimalImagesDTO> imageList;
}
