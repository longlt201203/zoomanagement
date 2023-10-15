package com.nhom3.zoomanagement.utils;

import com.nhom3.zoomanagement.accounts.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Account> {
    @Override
    public Optional<Account> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == "anonymousUser") {
            return Optional.empty();
        }
        Account principal = (Account) authentication.getPrincipal();
        return Optional.of(principal);
    }
}
