package com.swp.ZooManagement.accounts;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.EntityNotFoundErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsService extends AbstractZooManagementService<Account, String, CreateAccountDto, UpdateAccountDto, FilterAccountDto> {
    @Override
    protected void berforeCreate(Account entity) throws ZooManagementException {

    }

    @Override
    protected void berforeUpdate(Account oldEntity, Account newEntity) throws ZooManagementException {

    }

    @Override
    public Account delete(String id) throws ZooManagementException {
        Account account = findById(id);
        account.setStatus(AccountStatusEnum.INACTIVE);
        return repository.save(account);
    }

    public Account findByEmail(String email) throws ZooManagementException {
        Optional<Account> findResult = ((AccountsRepository) repository).findByEmail(email);
        if (findResult.isPresent()) {
            return findResult.get();
        }
        throw new ZooManagementException(new EntityNotFoundErrorReport("email", email));
    }
}
