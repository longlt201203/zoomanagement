package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.news.News;
import com.nhom3.zoomanagement.news.NewsDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements UserDetails {
    private String id;
    private String name;
    private Enums.RoleEnum role;
    private Enums.HumanGenderEnum gender;
    private String email;
    private String phoneNumber;
    private String avatar;
    private List<NewsDTO> newsList;
//    private Set<Enums.RoleEnum> roles = new HashSet<>();
    private Collection<? extends GrantedAuthority> authorities;



    public static AccountDTO fromAccount(Account account, boolean hasNewsList) {

        List<GrantedAuthority> authorities =  new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().toString()));
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setRole(account.getRole());
        accountDTO.setGender(account.getGender());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPhoneNumber(account.getPhoneNumber());
        accountDTO.setAvatar(account.getAvatar());
        accountDTO.setAuthorities(authorities);
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
//    public static AccountDTO build(Account account) {
////        List<GrantedAuthority> authorities = account.getRole().stream()
////                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
////                .collect(Collectors.toList());
//        List<GrantedAuthority> authorities =  new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(account.getRole().toString()));
//        return new AccountDTO(
//                account.getId(),
//                account.getName(),
//                account.getRole(),
//                account.getGender(),
//                account.getEmail(),
//                account.getAvatar(),
//                account.getPhoneNumber(),
//                account.getNewsList(),
//                authorities);
//    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
