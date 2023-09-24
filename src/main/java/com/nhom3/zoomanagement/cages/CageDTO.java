package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.animals.AnimalDTO;
import com.nhom3.zoomanagement.animalspecies.AnimalSpecie;
import com.nhom3.zoomanagement.animalspecies.AnimalSpecieDTO;
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
    public static CageDTO fromCage(Cage cage, boolean hasAnimalSpecie, boolean hasArea, boolean hasMeal) {
        CageDTO cageDTO = new CageDTO();
        cageDTO.setId(cage.getId());
        cageDTO.setCode(cage.getCode());
        cageDTO.setDescription(cage.getDescription());
        if (hasAnimalSpecie) {
            cageDTO.setAnimalSpecie(AnimalSpecieDTO.fromAnimalSpecie(cage.getAnimalSpecie(), false, false));
        }
        if(hasArea){
            cageDTO.setArea(AreaDTO.fromArea(cage.getArea(), false));
        }
        if(hasMeal){
            cageDTO.setMeal(MealDTO.fromMeal(cage.getMeal(), false));
        }
        return cageDTO;
    }

    public static List<CageDTO> fromCageList(List<Cage> cageList, boolean hasAnimalSpecie, boolean hasArea, boolean hasMeal) {
        List<CageDTO> cageDTOList = new ArrayList<>();
        for (Cage cage : cageList) {
            CageDTO cageDTO = fromCage(cage, hasAnimalSpecie, hasArea, hasMeal);
            cageDTOList.add(cageDTO);
        }
        return cageDTOList;
    }
    private Integer id;
    private String code;
    private String description;
    private AreaDTO area;
    private AnimalSpecieDTO animalSpecie;
    private MealDTO meal;

}
