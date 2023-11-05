package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.AccountGenderEnum;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAccountDto implements DtoBase<Account> {
    @IsEnum(enumClass = AccountGenderEnum.class)
    private String gender;

    @NotBlank(message = "First name must be not blank")
    @Size(max = 30, message = "Length of first name must not exceed 30")
    private String fname;

    @NotBlank(message = "Last name must be not blank")
    @Size(max = 30, message = "Length of last name must not exceed 30")
    private String lname;

    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phone;

    private String avt;

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setGender(AccountGenderEnum.valueOf(gender));
        account.setFname(fname);
        account.setLname(lname);
        account.setPhone(phone);
        account.setAvt(avt);
        return account;
    }
}
