package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import lombok.Data;

@Data
public class UpdateStatusDto implements DtoBase<Account> {
    @IsEnum(enumClass = AccountStatusEnum.class)
    private String status;

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setStatus(AccountStatusEnum.valueOf(this.status));
        return account;
    }
}
