package com.swp.ZooManagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountsService;
import com.swp.ZooManagement.errors.JwtErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class JwtProviderImpl implements JwtProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expire.time}")
    private int jwtExpireTime;

    @Value("${jwt.timezone}")
    private String jwtTimezone;

    @Autowired
    private AccountsService accountsService;

    @Override
    public String signToken(Account account) throws ZooManagementException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(jwtTimezone));
            return JWT.create()
                    .withSubject(account.getId())
                    .withIssuedAt(now.toInstant())
                    .withExpiresAt(now.toInstant().plus(jwtExpireTime, ChronoUnit.SECONDS))
                    .sign(algorithm);
        } catch (Exception e) {
            throw new ZooManagementException(new JwtErrorReport("Sign token error", new HashMap<>() {{
                put("accountInfo", account);
                put("errorMessage", e.getMessage());
            }}));
        }
    }

    @Override
    public Account verifyToken(String token) throws ZooManagementException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            DecodedJWT payload = JWT.require(algorithm)
                    .build()
                    .verify(token);
            String accountId = payload.getSubject();
            return accountsService.findById(accountId);
        } catch (Exception e) {
            throw new ZooManagementException(new JwtErrorReport("Verify token error", new HashMap<>() {{
                put("token", token);
                put("errorMessage", e.getMessage());
            }}));
        }
    }
}
