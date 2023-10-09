package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.news.NewsDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private String name;
    private Enums.RoleEnum role;
    private Enums.HumanGenderEnum gender;
    private String email;
    private String phoneNumber;
    private String avatar;
    private List<NewsDTO> newsList;


    public static AccountDTO fromAccount(Account account, boolean hasNewsList) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().toString()));

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setRole(account.getRole());
        accountDTO.setGender(account.getGender());
        accountDTO.setEmail(account.getEmail());


        accountDTO.setPhoneNumber(account.getPhoneNumber());
        accountDTO.setAvatar(account.getAvatar());
        if (hasNewsList) {
            accountDTO.setNewsList(NewsDTO.fromNewsList(account.getNewsList(), false));
        }
        return accountDTO;
    }

    public static List<AccountDTO> fromAccountList(List<Account> accountList, boolean hasNewsList) {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountDTO accountDTO = fromAccount(account, hasNewsList);
            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }

}
