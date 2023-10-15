package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.animal_species.AnimalSpeciesDTO;
import com.nhom3.zoomanagement.animals.AnimalDTO;
import com.nhom3.zoomanagement.areas.AreaDTO;
import com.nhom3.zoomanagement.cage_meals.CageMealDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CageDTO {
    public static CageDTO fromCage(Cage cage, boolean hasAnimalSpecies, boolean hasArea, boolean hasAnimal, boolean hasMealSchedule, boolean hasManagedBy, boolean hasCreatedBy) {
        CageDTO cageDTO = new CageDTO();
        cageDTO.setId(cage.getId());
        cageDTO.setCode(cage.getCode());
        cageDTO.setDescription(cage.getDescription());
        if (hasAnimalSpecies) {
            cageDTO.setAnimalSpecies(AnimalSpeciesDTO.fromAnimalSpecies(cage.getAnimalSpecies(), false, false, false));
        }
        if(hasArea){
            cageDTO.setArea(AreaDTO.fromArea(cage.getArea(), false, false));
        }
        if(hasMealSchedule){
            cageDTO.setCageMealList(CageMealDTO.fromCageMealList(cage.getMealScheduleList(), false, false, false));
        }
        if(hasAnimal){
            cageDTO.setAnimalList(AnimalDTO.fromAnimalList(cage.getAnimalList(), false, false, false, false));
        }
        if(hasManagedBy){
            cageDTO.setManagedBy(AccountDTO.fromAccount(cage.getManagedBy(), false));
        }
        if(hasCreatedBy){
            cageDTO.setCreatedBy(AccountDTO.fromAccount(cage.getCreatedBy(), false));
        }
        return cageDTO;
    }

    public static List<CageDTO> fromCageList(List<Cage> cageList,  boolean hasAnimalSpecies, boolean hasArea, boolean hasAnimal, boolean hasMeal, boolean hasManagedBy, boolean hasCreatedBy) {
        List<CageDTO> cageDTOList = new ArrayList<>();
        for (Cage cage : cageList) {
            CageDTO cageDTO = fromCage(cage, hasAnimalSpecies, hasArea, hasAnimal, hasMeal, hasManagedBy, hasCreatedBy);
            cageDTOList.add(cageDTO);
        }
        return cageDTOList;
    }
    private Integer id;
    private String code;
    private String description;
    private AreaDTO area;
    private AnimalSpeciesDTO animalSpecies;
    private List<AnimalDTO> animalList;
    private List<CageMealDTO> cageMealList;
    private AccountDTO createdBy;
    private AccountDTO managedBy;

}
