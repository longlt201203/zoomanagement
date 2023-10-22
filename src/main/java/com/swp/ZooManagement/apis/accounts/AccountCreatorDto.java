package com.swp.ZooManagement.apis.accounts;

import lombok.Data;

@Data
public class AccountCreatorDto {
    private String id;
    private String email;
    private String fname;
    private String lname;
    private String phone;
}
