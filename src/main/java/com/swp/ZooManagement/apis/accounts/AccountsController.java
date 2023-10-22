package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController extends AbstractZooManagementController<Account, String, CreateAccountDto, UpdateAccountDto, FilterAccountDto, AccountResponseDto> {
}
