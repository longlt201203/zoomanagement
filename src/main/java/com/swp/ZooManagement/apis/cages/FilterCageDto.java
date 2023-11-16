package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.core.FilterDtoBase;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;

import java.beans.ConstructorProperties;

public class FilterCageDto extends FilterDtoBase<Cage> {
    protected String accountId;

    public void setAccountId(String id) {
        accountId = id;
    }

    public FilterCageDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Cage toEntity() {
        Cage cage = new Cage();
        if (accountId != null) {
            Account manager = new Account();
            manager.setId(accountId);
            cage.setManagedBy(manager);
        }
        return cage;
    }
}
