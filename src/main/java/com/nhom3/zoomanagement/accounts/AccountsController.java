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
    @GetMapping("/")
    public List<AccountDTO> get() {
        return accountsService.get();
    }

    @Override
    @GetMapping("/{id}")
    public AccountDTO get(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.get(id);
    }

    @Override
    @PostMapping("/")
    public AccountDTO create(@RequestBody @Valid CreateAccountDTO dto) {
        return accountsService.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    public AccountDTO update(@PathVariable("id") String id, @RequestBody @Valid UpdateAccountDTO dto) throws BadRequestException {
        return accountsService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public AccountDTO delete(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.delete(id);
    }
}
