package com.swp.ZooManagement.security;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ZooManagementSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;

    private String getAccessToken(HttpServletRequest request) {
        String token = "";
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            StringBuilder builder = new StringBuilder(authorizationHeader);
            builder.delete(0, "Bearer ".length());
            token = builder.toString();
        }
        return token;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        Account account = null;
        try {
            account = jwtProvider.verifyToken(getAccessToken(request));
        } catch (ZooManagementException e) {
            // Do nothing
        }
        Authentication authentication = new ZooManagementAuthentication(account);
        context.setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
