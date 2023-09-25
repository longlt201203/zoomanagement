package com.nhom3.zoomanagement.animal_species;

import com.nhom3.zoomanagement.animals.AnimalsDTO;
import com.nhom3.zoomanagement.cages.CagesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSpeciesDTO {
    public static AnimalSpeciesDTO fromAnimalSpecie(AnimalSpecies animalSpecies, boolean hasAnimal, boolean hasCage) {
        AnimalSpeciesDTO animalSpeciesDTO = new AnimalSpeciesDTO();
        animalSpeciesDTO.setId(animalSpecies.getId());
        animalSpeciesDTO.setName(animalSpecies.getName());
        animalSpeciesDTO.setDescription(animalSpecies.getDescription());
        animalSpeciesDTO.setImage(animalSpecies.getImage());
        if (hasAnimal) {
            animalSpeciesDTO.setAnimalList(AnimalsDTO.fromAnimalList(animalSpecies.getAnimalList(), false, false));
        }
        if(hasCage){
            animalSpeciesDTO.setCageList(CagesDTO.fromCageList(animalSpecies.getCageList(), false, false,false));
        }
        return animalSpeciesDTO;
    }

    public static List<AnimalSpeciesDTO> fromAnimaSpecielList(List<AnimalSpecies> animalSpeciesList, boolean hasAnimal, boolean hasCage) {
        List<AnimalSpeciesDTO> animalSpeciesDTOList = new ArrayList<>();
        for (AnimalSpecies animalSpecies : animalSpeciesList) {
            AnimalSpeciesDTO animalSpeciesDTO = fromAnimalSpecie(animalSpecies, hasAnimal, hasCage);
            animalSpeciesDTOList.add(animalSpeciesDTO);
        }
        return animalSpeciesDTOList;
    }
    private Integer id;
    private String name;
    private String description;
    private String image;
    private List<CagesDTO> cageList;
    private List<AnimalsDTO> animalList;
}
