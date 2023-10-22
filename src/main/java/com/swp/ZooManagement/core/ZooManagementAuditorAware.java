package com.swp.ZooManagement.core;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.security.ZooManagementAuthentication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.PreparedStatement;
import java.util.Optional;

public class ZooManagementAuditorAware implements AuditorAware<Account> {
    @Override
    public Optional<Account> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        try {
            Account account = (Account) auth.getPrincipal();
            if (account != null) {
                return Optional.of(account);
            }
        } catch (Exception e) {

        }
        return Optional.empty();
    }
}
