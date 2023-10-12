package com.nhom3.zoomanagement.animal_species;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.animals.AnimalDTO;
import com.nhom3.zoomanagement.cages.CageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSpeciesDTO {
    public static AnimalSpeciesDTO fromAnimalSpecies(AnimalSpecies animalSpecies, boolean hasAnimal, boolean hasCage, boolean hasCreatedBy) {
        AnimalSpeciesDTO animalSpeciesDTO = new AnimalSpeciesDTO();
        animalSpeciesDTO.setId(animalSpecies.getId());
        animalSpeciesDTO.setName(animalSpecies.getName());
        animalSpeciesDTO.setDescription(animalSpecies.getDescription());
        animalSpeciesDTO.setImage(animalSpecies.getImage());
        if (hasAnimal) {
            animalSpeciesDTO.setAnimalList(AnimalDTO.fromAnimalList(animalSpecies.getAnimalList(), false, false, false, false));
        }
        if(hasCage){
            animalSpeciesDTO.setCageList(CageDTO.fromCageList(animalSpecies.getCageList(), false, false, false, false, false,false));
        }
        if(hasCreatedBy){
            animalSpeciesDTO.setCreatedBy(AccountDTO.fromAccount(animalSpecies.getCreatedBy(), false));
        }
        return animalSpeciesDTO;
    }

    public static List<AnimalSpeciesDTO> fromAnimaSpecieslList(List<AnimalSpecies> animalSpeciesList, boolean hasAnimal, boolean hasCage, boolean hasCreatedBy) {
        List<AnimalSpeciesDTO> animalSpeciesDTOList = new ArrayList<>();
        for (AnimalSpecies animalSpecies : animalSpeciesList) {
            AnimalSpeciesDTO animalSpeciesDTO = fromAnimalSpecies(animalSpecies, hasAnimal, hasCage, hasCreatedBy);
            animalSpeciesDTOList.add(animalSpeciesDTO);
        }
        return animalSpeciesDTOList;
    }
    private Integer id;
    private String name;
    private String description;
    private String image;
    private AccountDTO createdBy;
    private List<CageDTO> cageList;
    private List<AnimalDTO> animalList;
}
