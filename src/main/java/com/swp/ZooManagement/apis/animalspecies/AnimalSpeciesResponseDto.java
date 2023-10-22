package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import lombok.Data;

@Data
public class AnimalSpeciesResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private AccountCreatorDto createdBy;
}
