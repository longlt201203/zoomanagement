package com.nhom3.zoomanagement.utils.jwt;

import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.google.GoogleUserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.expire.time}")
    private long tokenExpireTime ;

    @Value("${jwt.secret}")
    private String secret;

    public String generateJwtToken(String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email",userEmail);
        claims.put("username",userEmail);

        return doGenerateToken(claims, userEmail);
//        GoogleUserInfo googleUserInfo = (GoogleUserInfo) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setSubject((googleUserInfo.getEmail()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + tokenExpireTime))
//                .signWith(key(), SignatureAlgorithm.HS256)
//                .compact();
    }
    public String generateJwtToken2(Authentication authentication) {

        AccountDTO userPrincipal = (AccountDTO) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + tokenExpireTime))
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
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
