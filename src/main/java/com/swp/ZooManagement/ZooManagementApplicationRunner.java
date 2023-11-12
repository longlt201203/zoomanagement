package com.swp.ZooManagement;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountsRepository;
import com.swp.ZooManagement.utils.enums.AccountGenderEnum;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ZooManagementApplicationRunner implements ApplicationRunner {
    @Value("${swp.zoomanagement.admin.email}")
    private String email;

    @Value("${swp.zoomanagement.admin.fname}")
    private String fname;

    @Value("${swp.zoomanagement.admin.lname}")
    private String lname;

    @Value("${swp.zoomanagement.admin.phone}")
    private String phone;

    @Value("${swp.zoomanagement.admin.gender}")
    private String gender;

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Account> findAccountResult = accountsRepository.findByEmail(email);
        if (findAccountResult.isEmpty()) {
            Account account = new Account();
            account.setEmail(email);
            account.setFname(fname);
            account.setLname(lname);
            account.setPhone(phone);
            account.setGender(AccountGenderEnum.valueOf(gender));
            account.setRole(AccountRoleEnum.ADMIN);
            account.setStatus(AccountStatusEnum.ACTIVE);
            accountsRepository.save(account);
            System.out.println("Admin initialized!");
        }
        System.out.println("Hello from runner!");
    }
}
