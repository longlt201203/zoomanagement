package com.swp.ZooManagement.security;

import com.swp.ZooManagement.apis.accounts.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class ZooManagementAuthentication implements Authentication {
    private final Account account;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account != null ? List.of(ZooManagementAuthority.of(account.getRole())) : List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Account getPrincipal() {
        return account;
    }

    @Override
    public boolean isAuthenticated() {
        return account != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Override
    public String getName() {
        return Account.class.getName();
    }

    public ZooManagementAuthentication(Account account) {
        this.account = account;
    }
}
