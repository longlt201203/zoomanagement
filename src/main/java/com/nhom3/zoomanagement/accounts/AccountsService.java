package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsService implements IAccountsService {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public List<AccountDTO> get() {
        List<Account> accounts = accountsRepository.findAll();
        List<AccountDTO> accountDTOS = AccountDTO.fromAccountList(accounts, false, false);
        return accountDTOS;
    }

    @Override
    public AccountDTO get(String id) throws BadRequestException {
        Account account = accountsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        AccountDTO accountDTO = AccountDTO.fromAccount(account, false, false);
        return accountDTO;
    }

    @Override
    public AccountDTO create(CreateAccountDTO dto) throws BadRequestException {
        Account creator = accountsRepository.findById(dto.getCreatedBy()).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        Account account = dto.toAccount();
        account.setCreatedBy(creator);
        AccountDTO accountDTO = AccountDTO.fromAccount(accountsRepository.save(account), false, false);
        return accountDTO;
    }

    @Override
    public AccountDTO update(String id, UpdateAccountDTO dto) throws BadRequestException {
        Account account = accountsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        Account updateAccount = dto.toAccount(account);
        AccountDTO accountDTO = AccountDTO.fromAccount(accountsRepository.save(updateAccount), false, false);
        return accountDTO;
    }

    @Override
    public AccountDTO delete(String id) throws BadRequestException {
        if (accountsRepository.existsById(id)) {
            accountsRepository.deleteById(id);
        } else {
            throw new BadRequestException(new ErrorReport("Account not found"));
        }
        return null;
    }
}
