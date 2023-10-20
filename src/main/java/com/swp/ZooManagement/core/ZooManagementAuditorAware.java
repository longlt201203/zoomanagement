package com.swp.ZooManagement.core;

import com.swp.ZooManagement.accounts.Account;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ZooManagementAuditorAware implements AuditorAware<Account> {
    @Override
    public Optional<Account> getCurrentAuditor() {
        return Optional.empty();
    }
}
