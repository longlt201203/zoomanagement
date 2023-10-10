package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountsController implements IAccountsController {
    @Autowired
    AccountsService accountsService;
    
    @Override
    @GetMapping("get-all")
    public List<AccountDTO> get() {
        return accountsService.get();
    }

    @Override
    @GetMapping("get-by-Id/{id}")
    public AccountDTO get(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.get(id);
    }

    @Override
    @PostMapping("create")
    public AccountDTO create(@RequestBody @Valid CreateAccountDTO dto) {
        return accountsService.create(dto);
    }

    @Override
    @PutMapping("update/{id}")
    public AccountDTO update(@PathVariable("id") String id, @RequestBody @Valid UpdateAccountDTO dto) throws BadRequestException {
        return accountsService.update(id, dto);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public AccountDTO delete(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.delete(id);
    }
}
