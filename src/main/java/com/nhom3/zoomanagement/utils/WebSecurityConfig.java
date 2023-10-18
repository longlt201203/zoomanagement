package com.nhom3.zoomanagement.utils;


import com.nhom3.zoomanagement.accounts.AccountsController;
import com.nhom3.zoomanagement.utils.jwt.AuthEntryPointJwt;
import com.nhom3.zoomanagement.utils.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    @Autowired
    public AuthTokenFilter authTokenFilter;
    
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("run");
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*");
            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
            return config;
        }));
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/test-login-google").permitAll()
                                .requestMatchers("accounts/login/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "news/get-all").permitAll()
                                .requestMatchers(HttpMethod.GET, "news/get-by-Id/**").permitAll()
                                
                                .requestMatchers(HttpMethod.GET, "orders/get-all").permitAll()
                                .requestMatchers(HttpMethod.GET, "orders/get-by-Id/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "orders/create").permitAll()
                                .requestMatchers(HttpMethod.PUT, "orders/update/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "orders/update-status/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "orders/delete/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "orders/send-email-order-info/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "orders/get-revenue").permitAll()
                                
                                .requestMatchers(HttpMethod.GET, "tickets/get-all").permitAll()
                                .requestMatchers(HttpMethod.GET, "tickets/get-by-Id/**").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        ;
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
