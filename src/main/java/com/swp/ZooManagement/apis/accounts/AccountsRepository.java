package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByPhone(String phone);
}
