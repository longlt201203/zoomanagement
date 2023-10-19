package com.nhom3.zoomanagement.accounts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface AccountsRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {
    Account findByEmail(String email);
}
