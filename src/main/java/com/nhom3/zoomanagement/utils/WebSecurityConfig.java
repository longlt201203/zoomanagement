package com.nhom3.zoomanagement.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableMethodSecurity

public class WebSecurityConfig {
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;

//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }

//  @Override
//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }

//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("run");
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*");
            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
//        config.setAllowCredentials(true);
            return config;

        }));

        http.csrf(csrf -> csrf.disable())
////                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .anyRequest().authenticated()
                );

//        http.authenticationProvider(authenticationProvider());

//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
