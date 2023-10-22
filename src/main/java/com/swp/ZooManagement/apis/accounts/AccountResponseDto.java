package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.utils.enums.AccountGenderEnum;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
public class AccountResponseDto {
    private String id;
    private String email;
    private AccountRoleEnum role;
    private AccountGenderEnum gender;
    private String fname;
    private String lname;
    private String phone;
    private String avt;
    private Instant createdAt;
    private AccountStatusEnum status = AccountStatusEnum.ACTIVE;
    private AccountCreatorDto createdBy;
}
