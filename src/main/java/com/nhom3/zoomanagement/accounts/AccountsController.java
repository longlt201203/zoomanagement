package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.jwt.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountsController implements IAccountsController{
    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    AccountsService accountsService;
    @Autowired
    JwtProvider jwtProvider;
    @Override
    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    public List<AccountDTO> get() {
        return accountsService.get();
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STAFF', 'TRAINER'})")
    public AccountDTO get(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.get(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    public AccountDTO create(@RequestBody @Valid CreateAccountDTO dto) throws BadRequestException {
        return accountsService.create(dto);
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    public AccountDTO update(@PathVariable("id") String id, @RequestBody @Valid UpdateAccountDTO dto) throws BadRequestException {
        return accountsService.update(id, dto);
    }

    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STAFF', 'TRAINER'})")
    @PutMapping("update-own-account")
    public AccountDTO updateOwnAccount(Authentication authentication, @RequestBody @Valid UpdateAccountDTO dto) throws BadRequestException {
        Account currrentAccount = (Account) authentication.getPrincipal();
        return accountsService.updateOwnAccount(currrentAccount, dto);
    }

    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    @PutMapping("toggle-status/{id}")
    public AccountDTO toggleStatus(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.toggleStatus(id);
    }

    @Override
    public AccountDTO delete(@PathVariable("id") String id) throws BadRequestException {
        return null;
    }
    
}
