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
    @NotBlank(message = "Id must be not blank")
    private String id;

    @NotBlank(message = "Name must be not blank")
    private String name;

    @NotBlank(message = "Gender must be not blank")
    @ValueOfEnum(enumClass = Enums.HumanGenderEnum.class, message = "Gender is invalid")
    private String gender;

    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phoneNumber;

    private String avatar;

    public Enums.HumanGenderEnum parseGender() {
        return Enums.HumanGenderEnum.valueOf(gender);
    }

    public Account toAccount(Account presentAccount) {
        Account account = new Account();
        account.setId(this.getId());
        account.setName(this.getName());
        account.setRole(presentAccount.getRole());
        account.setGender(this.parseGender());
        account.setPhoneNumber(this.getPhoneNumber());
        account.setAvatar(this.getAvatar());
        account.setEmail(presentAccount.getEmail());
        return account;
    }
}
