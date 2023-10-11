package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.ICrudService;
import org.springframework.stereotype.Service;

@Service
public interface IAccountsService extends ICrudService<AccountDTO, String, CreateAccountDTO, UpdateAccountDTO> {
    
}
