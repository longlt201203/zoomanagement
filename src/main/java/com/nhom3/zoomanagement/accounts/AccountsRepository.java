package com.nhom3.zoomanagement.accounts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
    
    Page<Account> findByFname(String fName, Pageable pageable);
}
