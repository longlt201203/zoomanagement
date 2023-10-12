package com.nhom3.zoomanagement.utils;

import com.nhom3.zoomanagement.accounts.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AuditingConfiguration {
    @Bean
    public AuditorAware<Account> auditorAwareImpl() {
        return new AuditorAwareImpl();
    }
}
