package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesResponseDto;
import com.swp.ZooManagement.apis.areas.AreaResponseDto;
import lombok.Data;

@Data
public class CageResponseDto {
    private Integer id;
    private String code;
    private String description;
    private AreaResponseDto area;
    private AnimalSpeciesResponseDto animalSpecies;
    private AccountCreatorDto managedBy;
    private AccountCreatorDto createdBy;
}
