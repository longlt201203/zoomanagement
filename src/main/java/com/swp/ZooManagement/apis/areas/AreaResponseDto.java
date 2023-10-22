package com.swp.ZooManagement.apis.areas;
import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import lombok.Data;

@Data
public class AreaResponseDto {
    private Integer id;
    private String code;
    private String name;
    private String location;
    private AccountCreatorDto createdBy;
}
