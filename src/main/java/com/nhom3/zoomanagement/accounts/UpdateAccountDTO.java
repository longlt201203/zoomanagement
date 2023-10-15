package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDTO {

    @NotBlank(message = "Fname must be not blank")
    private String fname;
    
    @NotBlank(message = "Lname must be not blank")
    private String lname;

    @NotBlank(message = "Gender must be not blank")
    @ValueOfEnum(enumClass = Enums.AccountGenderEnum.class, message = "Gender is invalid")
    private String gender;

    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phone;

    private String avt;

    public Enums.AccountGenderEnum parseGender() {
        return Enums.AccountGenderEnum.valueOf(gender);
    }

    public Account toAccount(Account presentAccount) {
        Account account = new Account();
        account.setFname(this.getFname());
        account.setLname(this.getLname());
        account.setRole(presentAccount.getRole());
        account.setGender(this.parseGender());
        account.setPhone(this.getPhone());
        account.setAvt(this.getAvt());
        account.setEmail(presentAccount.getEmail());
        account.setCreatedBy(presentAccount.getCreatedBy());
        account.setStatus(presentAccount.getStatus());
        return account;
    }
}
