package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.animalimages.AnimalImage;
import com.nhom3.zoomanagement.animalimages.AnimalImageDTO;
import com.nhom3.zoomanagement.animalspecies.AnimalSpecie;
import com.nhom3.zoomanagement.animalspecies.AnimalSpecieDTO;
import com.nhom3.zoomanagement.tests.Test;
import com.nhom3.zoomanagement.tests.TestDTO;
import com.nhom3.zoomanagement.tests.TestNYDTO;
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
    public static AnimalDTO fromAnimal(Animal animal, boolean hasAnimalImage, boolean hasSpecie) {
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
            animalDTO.setImageList(AnimalImageDTO.fromAnimalImageDTOList(animal.getImageList(), false));
        }
        if(hasSpecie){
            animalDTO.setSpecie(AnimalSpecieDTO.fromAnimalSpecie(animal.getSpecie(), false, false));
        }
        return animalDTO;
    }

    public static List<AnimalDTO> fromAnimalList(List<Animal> animalList, boolean hasAnimalImage, boolean hasSpecie) {
        List<AnimalDTO> animalDTOList = new ArrayList<>();
        for (Animal animal : animalList) {
            AnimalDTO animalDTO = fromAnimal(animal, hasAnimalImage, hasSpecie);
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
    private AnimalSpecieDTO specie;
    private List<AnimalImageDTO> imageList;
}
