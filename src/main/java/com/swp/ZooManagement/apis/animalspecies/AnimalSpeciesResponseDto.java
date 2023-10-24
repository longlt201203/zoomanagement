package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.animals.AnimalResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class AnimalSpeciesResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private AccountCreatorDto createdBy;
    private List<AnimalResponseDto> animals;
}
