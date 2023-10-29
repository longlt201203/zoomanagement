package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.FilterDtoBase;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.beans.ConstructorProperties;

public class FilterAccountDto extends FilterDtoBase<Account> {
    protected AccountRoleEnum role;
    @ConstructorProperties({ "page", "perPage", "role" })
    public FilterAccountDto(Integer page, Integer perPage, AccountRoleEnum role) {
        super(page, perPage);
        this.role = role;
    }

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setRole(role);
        return account;
    }

    @Override
    public PageRequest getPageRequest() {
        return super.getPageRequest().withSort(Sort.by(Sort.Order.asc("fname")));
    }
}
