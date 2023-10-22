package com.swp.ZooManagement.security;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Component;

@Component
public interface JwtProvider {
    String signToken(Account account) throws ZooManagementException;
    Account verifyToken(String token) throws ZooManagementException;
}
