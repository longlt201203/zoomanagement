package com.swp.ZooManagement.core;

import com.swp.ZooManagement.apis.accounts.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ZooManagementJpaConfig {
    @Bean
    public AuditorAware<Account> auditorProvider() {
        return new ZooManagementAuditorAware();
    }
}
