package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.animal_species.AnimalSpeciesDTO;
import com.nhom3.zoomanagement.areas.AreaDTO;
import com.nhom3.zoomanagement.meals.MealDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CageDTO {
    public static CageDTO fromCage(Cage cage, boolean hasAnimalSpecies, boolean hasArea, boolean hasMeal) {
        CageDTO cageDTO = new CageDTO();
        cageDTO.setId(cage.getId());
        cageDTO.setCode(cage.getCode());
        cageDTO.setDescription(cage.getDescription());
        if (hasAnimalSpecies) {
            cageDTO.setAnimalSpecies(AnimalSpeciesDTO.fromAnimalSpecie(cage.getAnimalSpecies(), false, false));
        }
        if(hasArea){
            cageDTO.setArea(AreaDTO.fromArea(cage.getArea(), false));
        }
        if(hasMeal){
            cageDTO.setMealList(MealDTO.fromMealList(cage.getMealList(), false));
        }
        return cageDTO;
    }

    public static List<CageDTO> fromCageList(List<Cage> cageList, boolean hasAnimalSpecies, boolean hasArea, boolean hasMeal) {
        List<CageDTO> cageDTOList = new ArrayList<>();
        for (Cage cage : cageList) {
            CageDTO cageDTO = fromCage(cage, hasAnimalSpecies, hasArea, hasMeal);
            cageDTOList.add(cageDTO);
        }
        return cageDTOList;
    }
    private Integer id;
    private String code;
    private String description;
    private AreaDTO area;
    private AnimalSpeciesDTO animalSpecies;
    private List<MealDTO> mealList;

}
