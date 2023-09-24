package com.nhom3.zoomanagement.animalspecies;

import com.nhom3.zoomanagement.animalimages.AnimalImageDTO;
import com.nhom3.zoomanagement.animals.Animal;
import com.nhom3.zoomanagement.animals.AnimalDTO;
import com.nhom3.zoomanagement.cages.CageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSpecieDTO {
    public static AnimalSpecieDTO fromAnimalSpecie(AnimalSpecie animalSpecie, boolean hasAnimal, boolean hasCage) {
        AnimalSpecieDTO animalSpecieDTO = new AnimalSpecieDTO();
        animalSpecieDTO.setId(animalSpecie.getId());
        animalSpecieDTO.setName(animalSpecie.getName());
        animalSpecieDTO.setDescription(animalSpecie.getDescription());
        animalSpecieDTO.setImage(animalSpecie.getImage());
        if (hasAnimal) {
            animalSpecieDTO.setAnimalList(AnimalDTO.fromAnimalList(animalSpecie.getAnimalList(), false, false));
        }
        if(hasCage){
            animalSpecieDTO.setCageList(CageDTO.fromCageList(animalSpecie.getCageList(), false, false,false));
        }
        return animalSpecieDTO;
    }

    public static List<AnimalSpecieDTO> fromAnimaSpecielList(List<AnimalSpecie> animalSpecieList, boolean hasAnimal, boolean hasCage) {
        List<AnimalSpecieDTO> animalSpecieDTOList = new ArrayList<>();
        for (AnimalSpecie animalSpecie : animalSpecieList) {
            AnimalSpecieDTO animalSpecieDTO = fromAnimalSpecie(animalSpecie, hasAnimal, hasCage);
            animalSpecieDTOList.add(animalSpecieDTO);
        }
        return animalSpecieDTOList;
    }
    private Integer id;
    private String name;
    private String description;
    private String image;
    private List<CageDTO> cageList;
    private List<AnimalDTO> animalList;
}
