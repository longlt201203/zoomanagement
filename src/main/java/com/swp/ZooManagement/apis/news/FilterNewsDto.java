package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterNewsDto extends FilterDtoBase<News> {

    protected String email;
    public FilterNewsDto(Integer page, Integer perPage, String email) {
        super(page, perPage);
        this.email = email;
    }

    @Override
    public News toEntity() {
        News news = new News();
        Account account = new Account();
        account.setEmail(email);
        news.setAuthor(account);
        return news;
    }
}
