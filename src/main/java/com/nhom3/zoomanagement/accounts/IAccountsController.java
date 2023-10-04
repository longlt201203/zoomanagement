package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.ICrudController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/accounts")
public interface IAccountsController extends ICrudController<AccountDTO, String, CreateAccountDTO, UpdateAccountDTO> {

}
