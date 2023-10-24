package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesResponseDto;
import com.swp.ZooManagement.apis.areas.AreaResponseDto;
import com.swp.ZooManagement.apis.cagemeals.CageMealResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class CageResponseDto {
    private Integer id;
    private String code;
    private String description;
    private AreaResponseDto area;
    private AnimalSpeciesResponseDto animalSpecies;
    private AccountCreatorDto managedBy;
    private AccountCreatorDto createdBy;
    private List<CageMealResponseDto> cageMeals;
}
