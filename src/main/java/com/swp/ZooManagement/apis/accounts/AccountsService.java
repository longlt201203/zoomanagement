package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.EntityNotFoundErrorReport;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsService extends AbstractZooManagementService<Account, String, CreateAccountDto, UpdateAccountDto, FilterAccountDto> {
    @Override
    protected void berforeCreate(Account entity) throws ZooManagementException {
        AccountsRepository repository = (AccountsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<Account> findResult;

        // Check unique email
        findResult = repository.findByEmail(entity.getEmail());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("email", entity.getEmail(), "Email already existed"));
        }

        // Check unique phone number
        findResult = repository.findByPhone(entity.getPhone());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("phone", entity.getPhone(), "Phone number already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void berforeUpdate(Account oldEntity, Account newEntity) throws ZooManagementException {
        AccountsRepository repository = (AccountsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<Account> findResult;

        // Check unique phone number
        findResult = repository.findByPhone(newEntity.getPhone());
        if (findResult.isPresent() && !findResult.get().getId().equals(oldEntity.getId())) {
            errors.add(new ValidationError("phone", newEntity.getPhone(), "Phone number already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setGender(newEntity.getGender());
        oldEntity.setFname(newEntity.getFname());
        oldEntity.setLname(newEntity.getLname());
        oldEntity.setPhone(newEntity.getPhone());
        oldEntity.setAvt(newEntity.getAvt());
        oldEntity.setStatus(newEntity.getStatus());
    }

    @Override
    public Account delete(String id) throws ZooManagementException {
        return null;
    }

    public Account findByEmail(String email) throws ZooManagementException {
        AccountsRepository repository = (AccountsRepository) this.repository;
        Optional<Account> findResult = repository.findByEmail(email);
        if (findResult.isPresent()) {
            return findResult.get();
        }
        throw new ZooManagementException(new EntityNotFoundErrorReport("email", email));
    }
}
