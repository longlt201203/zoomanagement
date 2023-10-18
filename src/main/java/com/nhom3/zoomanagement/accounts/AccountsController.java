package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.jwt.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("get-all")
    public List<AccountDTO> get() {
        return accountsService.get();
    }

    @Override
    @GetMapping("{id}")
    public AccountDTO get(@PathVariable("id") String id) throws BadRequestException {
        return accountsService.get(id);
    }

    @GetMapping("login/{id}")
    public String login(@PathVariable("id") String id) throws BadRequestException {
        Account account = accountsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        String token = jwtProvider.generateJwtToken(account.getEmail(), account.getRole().toString());
        return token;
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    @PostMapping("create")
    public AccountDTO create(@RequestBody @Valid CreateAccountDTO dto) throws BadRequestException {
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
