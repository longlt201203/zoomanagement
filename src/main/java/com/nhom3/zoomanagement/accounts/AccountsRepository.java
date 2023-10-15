package com.nhom3.zoomanagement.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
    
//    List<Account> findBy
}
