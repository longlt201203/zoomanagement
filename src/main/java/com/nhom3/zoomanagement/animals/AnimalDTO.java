package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.animal_images.AnimalImageDTO;
import com.nhom3.zoomanagement.animal_species.AnimalSpeciesDTO;
import com.nhom3.zoomanagement.cages.CageDTO;
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
public class AnimalDTO {
    public static AnimalDTO fromAnimal(Animal animal, boolean hasAnimalImage, boolean hasSpecies, boolean hasCage) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setNation(animal.getNation());
        animalDTO.setDob(animal.getDob());
        animalDTO.setGender(animal.getGender());
        animalDTO.setStatus(animal.getStatus());
        animalDTO.setDescription(animal.getDescription());
        animalDTO.setNote(animal.getNote());
        if (hasAnimalImage) {
            animalDTO.setImageList(AnimalImageDTO.fromAnimalImageList(animal.getImageList(), false));
        }
        if(hasSpecies){
            animalDTO.setSpecies(AnimalSpeciesDTO.fromAnimalSpecie(animal.getSpecies(), false, false));
        }
        if(hasCage){
            animalDTO.setCage(CageDTO.fromCage(animal.getCage(),false, false, false, false));
        }
        return animalDTO;
    }

    public static List<AnimalDTO> fromAnimalList(List<Animal> animalList, boolean hasAnimalImage, boolean hasSpecies, boolean hasCage) {
        List<AnimalDTO> animalDTOList = new ArrayList<>();
        for (Animal animal : animalList) {
            AnimalDTO animalDTO = fromAnimal(animal, hasAnimalImage, hasSpecies, hasCage);
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
    private CageDTO cage;
    private List<AnimalImageDTO> imageList;
}
