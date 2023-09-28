package com.nhom3.zoomanagement.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
@Getter
@NoArgsConstructor
public class GcpConfig implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(GcpConfig.class);
    @Value("${nhom3.zoomanagement.gcp.client-id}")
    private String clientId;

    @Value("${nhom3.zoomanagement.gcp.client-secret}")
    private String clientSecret;


    @Value("${jwt.expire.time}")
    private long tokenExpireTime ;

    @Value("${jwt.secret}")
    private String secret;

    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    //for retrieveing any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userEmail);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(key(),SignatureAlgorithm.HS512).compact();
    }

    //validate token
    public String validateToken(String token) {
        final String userEmail = getUserEmailFromToken(token);
        if(isTokenExpired(token)) System.out.println("error");

        return userEmail;
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
