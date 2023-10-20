package com.swp.ZooManagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.swp.ZooManagement.accounts.Account;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public interface JwtProvider {
    String signToken(Account account) throws ZooManagementException;
    Account verifyToken(String token) throws ZooManagementException;
}
