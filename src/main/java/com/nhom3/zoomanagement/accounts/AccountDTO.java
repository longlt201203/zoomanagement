package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.news.NewsDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private String fname;
    private String lname;
    private Enums.RoleEnum role;
    private Enums.AccountGenderEnum gender;
    private Enums.AccountStatusEnum status;
    private String email;
    private String phone;
    private String avt;
    private LocalDateTime createdAt;
    private List<NewsDTO> newsList;
    private AccountDTO createdBy;

    public static AccountDTO fromAccount(Account account, boolean hasNewsList, boolean hasCreatedBy) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().toString()));

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFname(account.getFname());
        accountDTO.setLname(account.getLname());
        accountDTO.setRole(account.getRole());
        accountDTO.setGender(account.getGender());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPhone(account.getPhone());
        accountDTO.setAvt(account.getAvt());
        if (hasNewsList) {
            accountDTO.setNewsList(NewsDTO.fromNewsList(account.getNewsList(), false));
        }
        if(hasCreatedBy) {
            accountDTO.setCreatedBy(AccountDTO.fromAccount(account.getCreatedBy(), false, false));
        }
        return accountDTO;
    }

    public static List<AccountDTO> fromAccountList(List<Account> accountList, boolean hasNewsList, boolean hasCreatedBy) {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountDTO accountDTO = fromAccount(account, hasNewsList, hasCreatedBy);
            accountDTOList.add(accountDTO);
        return accountDTOList;
    }

}
