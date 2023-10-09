package com.nhom3.zoomanagement.utils.jwt;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.accounts.AccountsController;
import com.nhom3.zoomanagement.accounts.AccountsRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;



public class AuthTokenFilter extends OncePerRequestFilter{
    @Autowired
    private JwtProvider jwtUtils;

    @Autowired
    private AccountsController accountsController;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            System.out.println("ahihi");

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String email = jwtUtils.getUserNameFromJwtToken(jwt);
                System.out.println(email);
//                Account account = accountsRepository.findByEmail(email).orElseThrow(null);
                UserDetails account = accountsController.loadUserByUsername(email);
//                System.out.println(account.);
                //role here
//                List<SimpleGrantedAuthority> grantedAuthorities =  new ArrayList<>();
//                grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().toString()));
//                List<GrantedAuthority> grantedAuthoritiess = account.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList()); // (1)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                account,
                                null,
                                account.getAuthorities());
                System.out.println("authen: "+ authentication.getPrincipal());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
//        System.out.println(headerAuth);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            System.out.println(headerAuth.substring(7));

            return headerAuth.substring(7);
        }

        return null;
    }
}
