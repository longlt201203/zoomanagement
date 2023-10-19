package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import com.nhom3.zoomanagement.utils.search_filter.SearchRequestDTO;
import com.nhom3.zoomanagement.utils.search_filter.SearchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        Account existAccountByEmail = accountsRepository.findByEmail(dto.getEmail());
        if (existAccountByEmail != null) throw new BadRequestException(new ErrorReport("Email existed"));
        if(dto.parseRole() == Enums.RoleEnum.ADMIN) throw new BadRequestException(new ErrorReport("Can not create account role Admin"));
        Account account = dto.toAccount();
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
        return null;
    }

    public AccountDTO updateOwnAccount(Account currrentAccount, UpdateAccountDTO dto) {
        currrentAccount = dto.toAccount(currrentAccount);
        AccountDTO accountDTO = AccountDTO.fromAccount(accountsRepository.save(currrentAccount), false, false);
        return accountDTO;
    }

    public AccountDTO toggleStatus(String id) throws BadRequestException {
        Account account = accountsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        Enums.AccountStatusEnum updateStatus = account.getStatus() == Enums.AccountStatusEnum.ACTIVE ? Enums.AccountStatusEnum.INACTIVE : Enums.AccountStatusEnum.ACTIVE;
        account.setStatus(updateStatus);
        AccountDTO accountDTO = AccountDTO.fromAccount(accountsRepository.save(account), false, false);
        return accountDTO;
    }

    public SearchResponseDTO<AccountDTO> search(int pageNum, int pageSize, SearchRequestDTO dto) {
        List<SearchCriteria> searchCriteriaList = dto.getSearchCriteriaList();
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());
        AccountSpecificationBuilder builder = new AccountSpecificationBuilder();
        
        if(searchCriteriaList != null) {
            searchCriteriaList.forEach(criteria -> {
                builder.with(criteria);
            });
        }
        
        Page<Account> accountPage = accountsRepository.findAll(builder.build(), page);
        
        List<AccountDTO> accountDTOS = AccountDTO.fromAccountList(accountPage.getContent(), false , false);
        Integer totalPages = accountPage.getTotalPages();
        Integer totalElements = Math.toIntExact(accountPage.getTotalElements());
        
        return new SearchResponseDTO<AccountDTO> (accountDTOS, totalPages, totalElements, pageNum);
    }
}
