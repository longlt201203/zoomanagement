package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountsController implements IAccountsController{
    @Autowired
    AccountsRepository accountsRepository;
    
    @Autowired
    JwtProvider jwtProvider;
    @Override
    @GetMapping("/")
    public List<AccountDTO> get() {
        return null;
    }

    @Override
    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    public AccountDTO get(@PathVariable("id") String id) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(id);
        return accountDTO;
    }

    @GetMapping("login/{id}")
    public String login(@PathVariable("id") String id) throws BadRequestException {
        Account account = accountsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        String token = jwtProvider.generateJwtToken(account.getEmail());
        return token;
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
