package com.nhom3.zoomanagement.accounts;


import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountsRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
}
