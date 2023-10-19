package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {
    @NotBlank(message = "FName must be not blank")
    @Size(max = 30, message = "Length of FName must not exceed 30")
    private String fName;
    
    @NotBlank(message = "LName must be not blank")
    @Size(max = 30, message = "Length of LName must not exceed 30")
    private String lName;

    @NotBlank(message = "Role must be not blank")
    @ValueOfEnum(enumClass = Enums.RoleEnum.class, message = "Role is invalid")
    private String role;

    @NotBlank(message = "Gender must be not blank")
    @ValueOfEnum(enumClass = Enums.AccountGenderEnum.class, message = "Gender is invalid")
    private String gender;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phone;

    private String avt;

    public Enums.RoleEnum parseRole() {
        return Enums.RoleEnum.valueOf(role);
    }

    public Enums.AccountGenderEnum parseGender() {
        return Enums.AccountGenderEnum.valueOf(gender);
    }
    
    public Account toAccount() {
        Account account = new Account();
        account.setFName(this.getFName());
        account.setLName(this.getLName());
        account.setRole(this.parseRole());
        account.setGender(this.parseGender());
        account.setEmail(this.getEmail());
        account.setPhone(this.getPhone());
        account.setAvt(this.getAvt());
        return account;
    }
}
