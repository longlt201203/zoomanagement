package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.apis.animals.AnimalResponseDto;
import com.swp.ZooManagement.apis.areas.AreaResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class CageResponseDto {
    private Integer id;
    private String code;
    private String name;
    private Integer capacity;
    private String description;
    private AreaResponseDto area;
    private AccountCreatorDto managedBy;
    private AccountCreatorDto createdBy;
    private List<AnimalResponseDto> animals;
}
