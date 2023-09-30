package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {
    @NotBlank(message = "Name must be not blank")
    @Size(max = 30, message = "Length of name must not exceed 30")
    private String name;
    
    @NotBlank(message = "Role must be not blank")
    @ValueOfEnum(enumClass = Enums.RoleEnum.class, message = "Role is invalid")
    private String role;

    @NotBlank(message = "Gender must be not blank")
    @ValueOfEnum(enumClass = Enums.HumanGenderEnum.class, message = "Gender is invalid")
    private String gender;
    
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email")
    private String email;
    
    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phoneNumber;
    
    private String avatar;
    
    public Enums.RoleEnum getRole() {
        return Enums.RoleEnum.valueOf(role);
    }

    public Enums.HumanGenderEnum getGender() {
        return Enums.HumanGenderEnum.valueOf(gender);
    }
}
