package com.swp.ZooManagement.security;

import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import org.springframework.security.core.GrantedAuthority;

public class ZooManagementAuthority implements GrantedAuthority {
    public static ZooManagementAuthority of(AccountRoleEnum role) {
        return new ZooManagementAuthority(role.getValue());
    }

    private final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    private ZooManagementAuthority(String authority) {
        this.authority = authority;
    }
}
