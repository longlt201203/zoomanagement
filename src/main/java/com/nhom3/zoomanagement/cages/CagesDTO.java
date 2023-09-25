package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.animal_species.AnimalSpeciesDTO;
import com.nhom3.zoomanagement.areas.AreasDTO;
import com.nhom3.zoomanagement.meals.MealsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CagesDTO {
    public static CagesDTO fromCage(Cage cage, boolean hasAnimalSpecies, boolean hasArea, boolean hasMeal) {
        CagesDTO cageDTO = new CagesDTO();
        cageDTO.setId(cage.getId());
        cageDTO.setCode(cage.getCode());
        cageDTO.setDescription(cage.getDescription());
        if (hasAnimalSpecies) {
            cageDTO.setAnimalSpecies(AnimalSpeciesDTO.fromAnimalSpecie(cage.getAnimalSpecies(), false, false));
        }
        if(hasArea){
            cageDTO.setArea(AreasDTO.fromArea(cage.getArea(), false));
        }
        if(hasMeal){
            cageDTO.setMeal(MealsDTO.fromMeal(cage.getMeal(), false));
        }
        return cageDTO;
    }

    public static List<CagesDTO> fromCageList(List<Cage> cageList, boolean hasAnimalSpecies, boolean hasArea, boolean hasMeal) {
        List<CagesDTO> cageDTOList = new ArrayList<>();
        for (Cage cage : cageList) {
            CagesDTO cageDTO = fromCage(cage, hasAnimalSpecies, hasArea, hasMeal);
            cageDTOList.add(cageDTO);
        }
        return cageDTOList;
    }
    private Integer id;
    private String code;
    private String description;
    private AreasDTO area;
    private AnimalSpeciesDTO animalSpecies;
    private MealsDTO meal;

}
