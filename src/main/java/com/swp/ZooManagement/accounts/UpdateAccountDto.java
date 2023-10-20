package com.swp.ZooManagement.accounts;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.enums.AccountGenderEnum;
import lombok.Data;

@Data
public class UpdateAccountDto implements DtoBase<Account> {
    private AccountGenderEnum gender;
    private String fname;
    private String lname;
    private String phone;
    private String avt;

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setGender(gender);
        account.setFname(fname);
        account.setLname(lname);
        account.setPhone(phone);
        account.setAvt(avt);
        return account;
    }
}
