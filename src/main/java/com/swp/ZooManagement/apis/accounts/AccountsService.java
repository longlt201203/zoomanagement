package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.apis.auth.AuthenticationService;
import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.core.ErrorReport;
import com.swp.ZooManagement.errors.EntityNotFoundErrorReport;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsService extends AbstractZooManagementService<Account, String, CreateAccountDto, UpdateAccountDto, FilterAccountDto> {
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<Account> findAll(FilterAccountDto filterAccountDto) {
        Account currentUser = authenticationService.getCurrentUser();
        if (currentUser.getRole() == AccountRoleEnum.STAFF) {
            filterAccountDto.setCreatedById(currentUser.getId());
        }
        return super.findAll(filterAccountDto);
    }

    @Override
    protected void beforeCreate(Account entity) throws ZooManagementException {
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
    protected void beforeUpdate(Account oldEntity, Account newEntity) throws ZooManagementException {
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

    public Account updateStatus(String id, UpdateStatusDto dto) throws ZooManagementException{
        Account currentUser = authenticationService.getCurrentUser();
        AccountsRepository repository = (AccountsRepository) this.repository;
        Account account = findById(id);
        if (account.getId().equals(currentUser.getId())) {
            throw new ZooManagementException(new ErrorReport<>("Cannot update status of your own account!", account.getId()));
        }
        account.setStatus(dto.toEntity().getStatus());
        account= repository.save(account);
        return account;
    }
}
