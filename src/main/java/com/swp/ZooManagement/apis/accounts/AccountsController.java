package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController extends AbstractZooManagementController<Account, String, CreateAccountDto, UpdateAccountDto, FilterAccountDto, AccountResponseDto> {
    @PutMapping ("/update-status/{id}")
    public AccountResponseDto updateStatus(@PathVariable("id") String id, @Valid @RequestBody UpdateStatusDto dto) throws ZooManagementException {
        AccountsService service = (AccountsService) this.service;
        Account account = service.updateStatus(id, dto);
        return account.toResponseDto();
    }
}
