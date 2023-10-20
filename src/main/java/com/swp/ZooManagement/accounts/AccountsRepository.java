package com.swp.ZooManagement.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
}
