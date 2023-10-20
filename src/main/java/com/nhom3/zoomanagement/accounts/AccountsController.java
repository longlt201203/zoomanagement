package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.utils.jwt.JwtProvider;
import com.nhom3.zoomanagement.utils.search_filter.SearchRequestDTO;
import com.nhom3.zoomanagement.utils.search_filter.SearchResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @PreAuthorize("hasAnyAuthority({'ADMIN'})")
    @PostMapping("search")
    public SearchResponseDTO<AccountDTO> search(@RequestParam(name = "pageNums", defaultValue = "0") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestBody SearchRequestDTO searchDTO) {
        return accountsService.search(pageNum, pageSize, searchDTO);
    }

    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STAFF', 'TRAINER'})")
    @GetMapping("get-self")
    public AccountDTO getCurrentAccount(Authentication authentication) throws BadRequestException {
        Account currrentAccount = (Account) authentication.getPrincipal();
        return AccountDTO.fromAccount(currrentAccount, false, false);
    }
    
}
