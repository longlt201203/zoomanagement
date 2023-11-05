/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.security;

import java.util.List;

import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author Le Thanh Long
 */
@Configuration
@EnableWebSecurity
public class ZooManagementSecurityConfiguration {
    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProviderImpl();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    ZooManagementSecurityFilter zooManagementSecurityFilter() {
        return new ZooManagementSecurityFilter();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(zooManagementSecurityFilter(), AuthorizationFilter.class)
                .authorizeHttpRequests(
//                        requests -> requests.anyRequest().permitAll()
                        requests -> requests
                                .requestMatchers(HttpMethod.GET, "/**")
                                .permitAll()
                                .requestMatchers("/utils/**", "/auth/**", "/payment/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/orders/**")
                                .permitAll()
                                .requestMatchers("/news/**", "/cages/**", "/animals/**")
                                .hasAuthority(AccountRoleEnum.STAFF.getValue())
                                .requestMatchers("/cage-meals/**", "/meal-records/**")
                                .hasAnyAuthority(AccountRoleEnum.STAFF.getValue(), AccountRoleEnum.TRAINER.getValue())
                                .anyRequest()
                                .hasAuthority(AccountRoleEnum.ADMIN.getValue())
                );
        return http.build();
    }
}