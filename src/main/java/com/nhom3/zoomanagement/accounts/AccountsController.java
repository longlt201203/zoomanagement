package com.nhom3.zoomanagement.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsController implements IAccountsController, UserDetailsService {
    @Autowired
    AccountsRepository accountsRepository;
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

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountsRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

        return AccountDTO.fromAccount(account, false);
    }
}
