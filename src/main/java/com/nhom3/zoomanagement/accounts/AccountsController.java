package com.nhom3.zoomanagement.accounts;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsController implements IAccountsController {
    @Override
    public List<AccountDTO> get() {
        return null;
    }

    @Override
    public AccountDTO get(String id) {
        return null;
    }

    @Override
    public AccountDTO create(CreateAccountDTO dto) {
        return null;
    }

    @Override
    public AccountDTO update(String id, UpdateAccountDTO dto) {
        return null;
    }

    @Override
    public AccountDTO delete(String id) {
        return null;
    }
}
