package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesResponseDto;
import com.swp.ZooManagement.apis.cages.CageResponseDto;
import com.swp.ZooManagement.utils.enums.AnimalGenderEnum;
import com.swp.ZooManagement.utils.enums.AnimalStatusEnum;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class AnimalResponseDto {
    private Integer id;
    private String name;
    private String nation;
    private Instant dob;
    private AnimalGenderEnum gender;
    private AnimalStatusEnum status;
    private String description;
    private String note;
    private List<String> imageList;
    private AnimalSpeciesResponseDto species;
    private CageResponseDto cage;
    private AccountCreatorDto createdBy;
    private Instant createdAt;
    private AccountCreatorDto updatedBy;
    private Instant updatedAt;
}
