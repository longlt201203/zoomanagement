package com.nhom3.zoomanagement.utils.jwt;


import com.nhom3.zoomanagement.accounts.AccountsController;
import com.nhom3.zoomanagement.accounts.AccountsRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtUtils;

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = parseJwt(request);
        if (jwt == null || !jwtUtils.validateJwtToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = jwtUtils.getUserNameFromJwtToken(jwt);
        UserDetails account = accountsRepository.findByEmail(email);
        if (account == null) {
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        account,
                        null,
                        account.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            System.out.println(headerAuth.substring(7));
            return headerAuth.substring(7);
        }
        return null;
    }
}
